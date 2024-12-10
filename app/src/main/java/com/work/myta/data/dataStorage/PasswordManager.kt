package com.work.myta.data.dataStorage

import android.content.Context


class PasswordManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_prefs"

        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }


    fun saveIsLoggedIn(isLoggedIn: Int) {
        sharedPreferences.edit().putInt(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getIsLoggedIn(): Int {
        return sharedPreferences.getInt(KEY_IS_LOGGED_IN, 0)
    }

    fun logout() {
        sharedPreferences.edit().putInt(KEY_IS_LOGGED_IN,-1).apply()
    }
}

