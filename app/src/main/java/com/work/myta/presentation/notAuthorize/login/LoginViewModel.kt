package com.work.myta.presentation.notAuthorize.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.myta.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun checkUserCredentials(phone: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByPhoneAndPassword(phone, password)
                if (user != null) {
                    _loginState.value = true
                } else {
                    _loginState.value = false
                    _errorMessage.value = "Неверный номер телефона или пароль"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка проверки: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
