package com.example.hairbook.feature.appointments.domain.usecase

import com.example.hairbook.feature.appointments.domain.repository.AppointmentsRepository
import javax.inject.Inject

class CancelAppointmentUseCase @Inject constructor(
    private val repository: AppointmentsRepository
) {
    suspend operator fun invoke(id: String) = repository.cancelAppointment(id)
}
