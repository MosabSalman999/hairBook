package com.example.hairbook.feature.gallery.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GalleryRoute(viewModel: GalleryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    GalleryScreen(uiState = uiState, onEvent = viewModel::onEvent)
}
