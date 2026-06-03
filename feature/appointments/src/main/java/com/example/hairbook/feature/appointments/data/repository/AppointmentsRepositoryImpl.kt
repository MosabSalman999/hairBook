package com.example.hairbook.feature.appointments.data.repository

import com.example.hairbook.core.database.dao.AppointmentDao
import com.example.hairbook.feature.appointments.data.mapper.toDomain
import com.example.hairbook.feature.appointments.data.mapper.toEntity
import com.example.hairbook.feature.appointments.domain.model.Appointment
import com.example.hairbook.feature.appointments.domain.repository.AppointmentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppointmentsRepositoryImpl @Inject constructor(
    private val appointmentDao: AppointmentDao
) : AppointmentsRepository {
    override fun observeAppointments(): Flow<List<Appointment>> {
        return appointmentDao.observeAppointments().map { appointments -> appointments.map { it.toDomain() } }
    }

    override fun observeAppointment(id: String): Flow<Appointment?> {
        return appointmentDao.observeAppointment(id).map { it?.toDomain() }
    }

    override suspend fun saveAppointment(appointment: Appointment) {
        appointmentDao.upsertAppointment(appointment.toEntity())
    }

    override suspend fun cancelAppointment(id: String) {
        val current = appointmentDao.observeAppointment(id).first() ?: return
        appointmentDao.upsertAppointment(current.copy(isCancelled = true))
    }
}
