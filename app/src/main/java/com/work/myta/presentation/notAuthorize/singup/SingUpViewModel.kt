package com.work.myta.presentation.notAuthorize.singup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.getCredential
import java.util.concurrent.TimeUnit

class SingUpViewModel :ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(phoneAuthCallbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
        _authState.value = AuthState.Loading
    }

    fun verifyCode(code: String) {
        val currentVerificationId = verificationId
        if (currentVerificationId != null) {
            val credential = getCredential(currentVerificationId, code)
            signInWithPhoneAuthCredential(credential)
        } else {
            _authState.value = AuthState.Error("Verification ID is null")
        }
    }

    fun resendCode(phoneNumber: String) {
        val token = resendToken
        if (token != null) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setForceResendingToken(token)
                .setCallbacks(phoneAuthCallbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
            _authState.value = AuthState.Loading
        } else {
            _authState.value = AuthState.Error("Resend token is null")
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    _authState.value = AuthState.Success(user)
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    private val phoneAuthCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            _authState.value = AuthState.Error(e.message ?: "Verification failed")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            this@SingUpViewModel.verificationId = verificationId
            this@SingUpViewModel.resendToken = token
            _authState.value = AuthState.CodeSent
        }
    }
}

sealed class AuthState {
    object Loading : AuthState()
    object CodeSent : AuthState()
    data class Success(val user: FirebaseUser?) : AuthState()
    data class Error(val message: String) : AuthState()
}