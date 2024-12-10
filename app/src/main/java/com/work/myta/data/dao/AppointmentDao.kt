package com.work.myta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.work.myta.domain.entity.Appointment
import java.sql.Date

@Dao
interface AppointmentDao {

    // Insert a new appointment
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    // Insert multiple appointments
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointments(appointments: List<Appointment>)

    // Update an existing appointment
    @Update
    suspend fun updateAppointment(appointment: Appointment)

    // Delete an appointment
    @Delete
    suspend fun deleteAppointment(appointment: Appointment)

    // Get all appointments
    @Query("SELECT * FROM app_database_appointment")
    suspend fun getAllAppointments(): List<Appointment>

    // Get appointments by userId
    @Query("SELECT * FROM app_database_appointment WHERE userId = :userId")
    suspend fun getAppointmentsByUserId(userId: Int): List<Appointment>

    // Get appointments by type
    @Query("SELECT * FROM app_database_appointment WHERE type = :type")
    suspend fun getAppointmentsByType(type: String): List<Appointment>


}
