package com.example.hairbook.feature.clients.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairbook.feature.clients.domain.usecase.GetClientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    getClientsUseCase: GetClientsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ClientsUiState())
    val uiState: StateFlow<ClientsUiState> = _uiState.asStateFlow()

    init {
        getClientsUseCase()
            .onEach { clients -> _uiState.value = _uiState.value.copy(clients = clients) }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: ClientsUiEvent) {
        when (event) {
            ClientsUiEvent.AddClientClicked -> Unit
        }
    }
}
