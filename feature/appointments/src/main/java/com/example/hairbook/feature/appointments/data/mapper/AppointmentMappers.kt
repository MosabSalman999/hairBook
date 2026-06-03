package com.example.hairbook.feature.appointments.data.mapper

import com.example.hairbook.core.database.entity.AppointmentEntity
import com.example.hairbook.feature.appointments.domain.model.Appointment

fun AppointmentEntity.toDomain(): Appointment = Appointment(
    id = id,
    clientId = clientId,
    startsAtMillis = startsAtMillis,
    serviceName = serviceName,
    notes = notes,
    isCancelled = isCancelled
)

fun Appointment.toEntity(): AppointmentEntity = AppointmentEntity(
    id = id,
    clientId = clientId,
    startsAtMillis = startsAtMillis,
    serviceName = serviceName,
    notes = notes,
    isCancelled = isCancelled
)
