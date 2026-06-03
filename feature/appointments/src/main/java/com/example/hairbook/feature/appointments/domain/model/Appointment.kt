package com.example.hairbook.feature.appointments.domain.model

data class Appointment(
    val id: String,
    val clientId: String,
    val startsAtMillis: Long,
    val serviceName: String,
    val notes: String?,
    val isCancelled: Boolean
)
