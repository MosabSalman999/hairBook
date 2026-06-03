package com.example.hairbook.feature.gallery.ui

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
fun GalleryScreen(
    uiState: GalleryUiState,
    onEvent: (GalleryUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = "Gallery") }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiState.photos.isEmpty()) {
                EmptyState(title = "No photos yet", message = "Client style photos will appear here.")
            } else {
                uiState.photos.forEach { photo ->
                    Text(text = photo.caption ?: photo.uri)
                }
            }
        }
    }
}
