package com.example.hairbook.feature.appointments.domain.usecase

import com.example.hairbook.feature.appointments.domain.repository.AppointmentsRepository
import javax.inject.Inject

class GetAppointmentsUseCase @Inject constructor(
    private val repository: AppointmentsRepository
) {
    operator fun invoke() = repository.observeAppointments()
}
