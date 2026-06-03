package com.example.hairbook.feature.appointments.domain.repository

import com.example.hairbook.feature.appointments.domain.model.Appointment
import kotlinx.coroutines.flow.Flow

interface AppointmentsRepository {
    fun observeAppointments(): Flow<List<Appointment>>
    fun observeAppointment(id: String): Flow<Appointment?>
    suspend fun saveAppointment(appointment: Appointment)
    suspend fun cancelAppointment(id: String)
}
