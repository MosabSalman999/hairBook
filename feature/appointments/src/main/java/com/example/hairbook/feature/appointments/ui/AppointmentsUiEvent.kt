package com.example.hairbook.feature.appointments.ui

sealed interface AppointmentsUiEvent {
    data object AddAppointmentClicked : AppointmentsUiEvent
}
