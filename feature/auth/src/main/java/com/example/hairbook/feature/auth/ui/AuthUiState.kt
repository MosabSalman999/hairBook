package com.example.hairbook.feature.auth.ui

data class AuthUiState(
    val isLoading: Boolean = false,
    val currentUserName: String? = null,
    val errorMessage: String? = null
)
