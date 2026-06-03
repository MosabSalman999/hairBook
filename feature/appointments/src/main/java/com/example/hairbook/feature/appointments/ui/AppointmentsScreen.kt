package com.example.hairbook.feature.appointments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hairbook.core.ui.component.EmptyState
import com.example.hairbook.core.ui.component.HairBookTopBar

@Composable
fun AppointmentsScreen(
    uiState: AppointmentsUiState,
    onEvent: (AppointmentsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = "Appointments") }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiState.appointments.isEmpty()) {
                EmptyState(title = "No appointments", message = "Scheduled visits will appear here.")
            } else {
                uiState.appointments.forEach { appointment ->
                    Text(text = appointment.serviceName)
                }
            }
        }
    }
}
