package com.work.myta.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_database")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String,
    val password: String,
    val email: String

)

