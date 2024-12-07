package com.work.myta.data.dataStorage


import androidx.room.Database
import androidx.room.RoomDatabase
import com.work.myta.domain.UserDao
import com.work.myta.domain.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
