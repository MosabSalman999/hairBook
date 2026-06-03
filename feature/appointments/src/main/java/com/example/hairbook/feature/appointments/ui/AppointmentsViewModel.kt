package com.example.hairbook.feature.appointments.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairbook.feature.appointments.domain.usecase.GetAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    getAppointmentsUseCase: GetAppointmentsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppointmentsUiState())
    val uiState: StateFlow<AppointmentsUiState> = _uiState.asStateFlow()

    init {
        getAppointmentsUseCase()
            .onEach { appointments -> _uiState.value = _uiState.value.copy(appointments = appointments) }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: AppointmentsUiEvent) {
        when (event) {
            AppointmentsUiEvent.AddAppointmentClicked -> Unit
        }
    }
}
