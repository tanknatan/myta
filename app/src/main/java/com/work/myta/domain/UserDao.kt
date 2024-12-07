package com.work.myta.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.work.myta.domain.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

    @Query("SELECT * FROM app_database WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: Int): User?
}


