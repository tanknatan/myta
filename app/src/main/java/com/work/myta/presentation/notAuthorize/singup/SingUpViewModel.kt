package com.work.myta.presentation.notAuthorize.singup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.domain.entity.User
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: AppRepositoryImpl
    private var generatedCode: String? = null

    // LiveData для наблюдения за пользователем
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        // Получаем доступ к репозиторию
        val userDao = DatabaseProvider.getDatabase(application).userDao()
        userRepository = com.work.myta.data.repository.AppRepositoryImpl
    }

    /**
     * Генерация случайного кода подтверждения.
     */
    fun generateVerificationCode(): String {
        val code = (1000..9999).random().toString() // Генерация случайного четырехзначного числа
        generatedCode = code // Сохраняем код в ViewModel
        return code
    }

    /**
     * Проверка введенного кода.
     * @param inputCode Код, введенный пользователем.
     * @return `true`, если коды совпадают, иначе `false`.
     */
    fun verifyCode(inputCode: String): Boolean {
        return inputCode == generatedCode
    }

    /**
     * Очистка сгенерированного кода после проверки.
     */
    fun clearCode() {
        generatedCode = null
    }

    /**
     * Сохранение данных пользователя в базе данных через репозиторий.
     */
    fun saveUserData(name: String, phone: String, email: String, password: String) {
        viewModelScope.launch {
            val user = User(name = name, phone = phone, email = email, password = password)
            userRepository.insert(user)
            _user.value = user // Обновляем LiveData
        }
    }

    /**
     * Отправка кода подтверждения на телефон.
     * (В реальном приложении здесь будет код для отправки через SMS)
     */
    fun sendVerificationCode(phone: String): String {
        val verificationCode = generateVerificationCode() // Генерация кода
        println("Код подтверждения для $phone: $verificationCode") // Логика отправки кода через SMS (заглушка)
        return verificationCode
    }

    /**
     * Получение пользователя по ID из базы данных.
     */
    fun getUserById(id: Int): LiveData<User?> {
        val liveData = MutableLiveData<User?>()
        viewModelScope.launch {
            liveData.postValue(userRepository.getUserById())
        }
        return liveData
    }
    // Функция проверки ввода
    fun validateInputs(
        name: String,
        phone: String,
        email: String,
        password: String,
        confirmPassword: String,
        isNameErrorSetter: (Boolean) -> Unit,
        isPhoneErrorSetter: (Boolean) -> Unit,
        isEmailErrorSetter: (Boolean) -> Unit,
        isPasswordErrorSetter: (Boolean) -> Unit,
        isConfirmPasswordErrorSetter: (Boolean) -> Unit
    ): Boolean {
        var isValid = true

        if (name.isBlank()) {
            isNameErrorSetter(true)
            isValid = false
        }

        if (phone.isBlank()) {
            isPhoneErrorSetter(true)
            isValid = false
        }

        if (email.isBlank()) {
            isEmailErrorSetter(true)
            isValid = false
        }

        if (password.isBlank()) {
            isPasswordErrorSetter(true)
            isValid = false
        }

        if (confirmPassword.isBlank() || confirmPassword != password) {
            isConfirmPasswordErrorSetter(true)
            isValid = false
        }

        return isValid
    }
}



