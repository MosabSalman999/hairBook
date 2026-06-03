package com.example.hairbook.feature.clients.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ClientsRoute(viewModel: ClientsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    ClientsScreen(uiState = uiState, onEvent = viewModel::onEvent)
}
