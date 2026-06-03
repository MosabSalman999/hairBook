package com.example.hairbook.feature.appointments.ui

import com.example.hairbook.feature.appointments.domain.model.Appointment

data class AppointmentsUiState(
    val isLoading: Boolean = false,
    val appointments: List<Appointment> = emptyList(),
    val errorMessage: String? = null
)
