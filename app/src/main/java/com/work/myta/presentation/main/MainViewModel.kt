package com.work.myta.presentation.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.domain.repository.AppRepository
import com.work.myta.domain.usecase.GetAuthStateUseCase
import com.work.myta.domain.usecase.SaveAuthStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = AppRepositoryImpl


        //val logState = MutableLiveData<Int>()

    val getAuthState = GetAuthStateUseCase(repository)

    val logState = MutableStateFlow(0) // начальное значение состояния


    private val userRepository: AppRepositoryImpl
    init {
        // Получаем доступ к репозиторию
        val userDao = DatabaseProvider.getDatabase(application).userDao()
        userRepository = AppRepositoryImpl
        logState.value = getAuthState.invoke()
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




    val saveAuthState = SaveAuthStateUseCase(repository)

    fun saveState(phone: String, password: String) {
        viewModelScope.launch {
            Log.d("LoginViewModel", "State saved: ${logState.value}")
            saveAuthState.invoke(userRepository.getIdByPhoneAndPassword(phone, password))
            logState.value = userRepository.getIdByPhoneAndPassword(phone, password)
            Log.d("LoginViewModel", "State saved: ${logState.value}")
        }


    }
}