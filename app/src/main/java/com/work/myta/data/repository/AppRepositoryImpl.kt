package com.work.myta.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.work.myta.data.dao.AppointmentDao
import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.dao.UserDao
import com.work.myta.domain.entity.Appointment
import com.work.myta.domain.entity.User
import com.work.myta.domain.repository.AppRepository

object AppRepositoryImpl : AppRepository {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var userDao: UserDao
    private lateinit var appointmentDao: AppointmentDao
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(KEY_IS_LOGGED_IN, Context.MODE_PRIVATE)
        userDao = DatabaseProvider.getDatabase(context).userDao()
        appointmentDao = DatabaseProvider.getDatabase(context).appointmentDao()
    }

    private const val KEY_IS_LOGGED_IN = "is_logged_in"


    override fun getAuthState(): Int {
        return sharedPreferences.getInt(KEY_IS_LOGGED_IN, 0)
    }

    override fun saveAuthState(id: Int) {
        sharedPreferences.edit().putInt(KEY_IS_LOGGED_IN, id).apply()
    }

    override fun logout() {
        sharedPreferences.edit().putInt(KEY_IS_LOGGED_IN, -1).apply()
    }

    override fun updateState() {
        TODO("Not yet implemented")
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    // Обновление пользователя
    suspend fun update(user: User) {
        userDao.update(user)
    }

    // Удаление пользователя
    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun insert(appointment: Appointment) {
        appointmentDao.insert(appointment)
    }

    // Получение пользователя по ID
    suspend fun getUserById(): User {
        return userDao.getUserById(sharedPreferences.getInt(KEY_IS_LOGGED_IN, 0))
    }

    suspend fun getUserByPhoneAndPassword(phone: String, password: String): User? {
        return userDao.getUserByPhoneAndPassword(phone, password)
    }

    suspend fun getIdByPhoneAndPassword(phone: String, password: String): Int {
        return userDao.getIdByPhoneAndPassword(phone, password)
    }

    suspend fun getAppointmentsByUserId(): List<Appointment> {
        return appointmentDao.getAppointmentsByUserId(sharedPreferences.getInt(KEY_IS_LOGGED_IN, 0))
    }


}
