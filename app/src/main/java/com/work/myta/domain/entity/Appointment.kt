package com.work.myta.domain.entity

import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.util.Date


@Entity(
    tableName = "app_database_appointment",
    foreignKeys = [
        ForeignKey(
            entity = User::class,               // Reference to the User entity
            parentColumns = ["id"],            // Primary key in the User table
            childColumns = ["userId"],         // Foreign key in the Appointment table
            onDelete = ForeignKey.CASCADE      // Optional: Cascade delete
        )
    ],
    indices = [
        androidx.room.Index(
            value = ["userId"],
            unique = true
        )
    ]
)
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Primary key for Appointment
    val type: String,
    val userId: Int, // Foreign key referencing User.id
    val appointmentDate: String
)
