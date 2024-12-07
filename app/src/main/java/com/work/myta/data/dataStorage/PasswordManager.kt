package com.work.myta.data.dataStorage

import android.content.Context



class PasswordManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_PASSWORD = "password"
        private const val KEY_PHONE_NUMBER = "phone_number"
    }

    // Сохранение пароля
    fun savePassword(password: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    // Получение пароля
    fun getPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }

    // Удаление пароля
    fun clearPassword() {
        sharedPreferences.edit().remove(KEY_PASSWORD).apply()
    }

    // Сохранение номера телефона
    fun savePhoneNumber(phoneNumber: String) {
        sharedPreferences.edit().putString(KEY_PHONE_NUMBER, phoneNumber).apply()
    }

    // Получение номера телефона
    fun getPhoneNumber(): String? {
        return sharedPreferences.getString(KEY_PHONE_NUMBER, null)
    }

    // Удаление номера телефона
    fun clearPhoneNumber() {
        sharedPreferences.edit().remove(KEY_PHONE_NUMBER).apply()
    }
}


