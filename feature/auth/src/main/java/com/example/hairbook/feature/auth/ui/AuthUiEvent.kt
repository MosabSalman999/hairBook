package com.example.hairbook.feature.auth.ui

sealed interface AuthUiEvent {
    data object SignInClicked : AuthUiEvent
}
