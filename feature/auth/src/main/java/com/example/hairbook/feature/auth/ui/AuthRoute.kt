package com.example.hairbook.feature.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthRoute(
    onSignedIn: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AuthScreen(
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                AuthUiEvent.SignInClicked -> {
                    viewModel.onEvent(event)
                    onSignedIn()
                }
            }
        }
    )
}
