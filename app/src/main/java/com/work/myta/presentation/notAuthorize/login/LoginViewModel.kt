package com.work.myta.presentation.notAuthorize.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    init {
        // Получаем доступ к репозиторию
        val userDao = DatabaseProvider.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun checkUserCredentials(phone: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByPhoneAndPassword(phone, password)
                if (user != null) {
                    Log.d("LoginViewModel", "User found: $user")
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
