package com.example.hairbook.feature.appointments.domain.usecase

import com.example.hairbook.feature.appointments.domain.model.Appointment
import com.example.hairbook.feature.appointments.domain.repository.AppointmentsRepository
import javax.inject.Inject

class SaveAppointmentUseCase @Inject constructor(
    private val repository: AppointmentsRepository
) {
    suspend operator fun invoke(appointment: Appointment) = repository.saveAppointment(appointment)
}
