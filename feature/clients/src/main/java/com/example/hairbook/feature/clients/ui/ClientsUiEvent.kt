package com.example.hairbook.feature.clients.ui

sealed interface ClientsUiEvent {
    data object AddClientClicked : ClientsUiEvent
}
