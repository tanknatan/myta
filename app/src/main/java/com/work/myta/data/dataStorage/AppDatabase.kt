package com.work.myta.data.dataStorage


import androidx.room.Database
import androidx.room.RoomDatabase
import com.work.myta.data.dao.AppointmentDao
import com.work.myta.data.dao.UserDao
import com.work.myta.domain.entity.Appointment
import com.work.myta.domain.entity.User

@Database(entities = [User::class, Appointment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao
}
