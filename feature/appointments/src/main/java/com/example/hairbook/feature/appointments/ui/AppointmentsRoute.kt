package com.example.hairbook.feature.appointments.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppointmentsRoute(viewModel: AppointmentsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    AppointmentsScreen(uiState = uiState, onEvent = viewModel::onEvent)
}
