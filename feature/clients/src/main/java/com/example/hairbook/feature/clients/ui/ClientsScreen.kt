package com.example.hairbook.feature.clients.ui

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
fun ClientsScreen(
    uiState: ClientsUiState,
    onEvent: (ClientsUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = "Clients") }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiState.clients.isEmpty()) {
                EmptyState(title = "No clients yet", message = "Add clients to start building their hair history.")
            } else {
                uiState.clients.forEach { client ->
                    Text(text = client.name)
                }
            }
        }
    }
}
