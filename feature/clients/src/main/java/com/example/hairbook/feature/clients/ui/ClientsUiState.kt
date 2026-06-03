package com.example.hairbook.feature.clients.ui

import com.example.hairbook.feature.clients.domain.model.Client

data class ClientsUiState(
    val isLoading: Boolean = false,
    val clients: List<Client> = emptyList(),
    val errorMessage: String? = null
)
