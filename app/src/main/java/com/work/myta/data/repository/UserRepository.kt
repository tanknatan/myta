package com.work.myta.data.repository

import androidx.lifecycle.LiveData
import com.work.myta.domain.UserDao
import com.work.myta.domain.entity.User

class UserRepository(private val userDao: UserDao) {

    // Получение всех пользователей (LiveData для наблюдения)

    // Вставка пользователя
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

    // Получение пользователя по ID
    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }
    suspend fun getUserByPhoneAndPassword(phone: String, password: String): User? {
        return userDao.getUserByPhoneAndPassword(phone, password)
    }
}
