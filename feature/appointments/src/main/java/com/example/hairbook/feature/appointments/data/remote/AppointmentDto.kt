package com.example.hairbook.feature.appointments.data.remote

data class AppointmentDto(
    val id: String,
    val clientId: String,
    val startsAtMillis: Long,
    val serviceName: String,
    val notes: String?,
    val isCancelled: Boolean
)
