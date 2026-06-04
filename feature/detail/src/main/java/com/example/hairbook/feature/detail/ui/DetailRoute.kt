package com.example.hairbook.feature.detail.ui

import androidx.compose.runtime.Composable

@Composable
fun DetailRoute(onNavigateUp: () -> Unit = {}) {
    DetailScreen(onNavigateUp = onNavigateUp)
}
