package com.example.hairbook.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey val id: String,
    val clientId: String,
    val startsAtMillis: Long,
    val serviceName: String,
    val notes: String? = null,
    val isCancelled: Boolean = false
)
