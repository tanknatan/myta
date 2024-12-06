package com.work.myta.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.work.myta.domain.entity.AuthState
import com.work.myta.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AppRepository {
    override fun getAuthState(): AuthState {
        return if (firebaseAuth.currentUser != null) {
            AuthState.Authorized
        } else {
            AuthState.NotAuthorized
        }
    }

    override fun performAuthResult() {
        // Логика обработки результата авторизации
    }
}

