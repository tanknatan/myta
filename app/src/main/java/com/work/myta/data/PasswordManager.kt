package com.work.myta.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class PasswordManager(context: Context) {

    private val sharedPreferences = createEncryptedPreferences(context)

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_PASSWORD = "password"
        private const val KEY_PHONE_NUMBER = "phone_number"
    }

    // Создаем EncryptedSharedPreferences
    private fun createEncryptedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            PREFS_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

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

