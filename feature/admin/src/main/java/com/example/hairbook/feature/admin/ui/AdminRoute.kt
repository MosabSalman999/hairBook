package com.example.hairbook.feature.admin.ui

import androidx.compose.runtime.Composable

@Composable
fun AdminRoute(onNavigateUp: () -> Unit = {}) {
    AdminScreen(onNavigateUp = onNavigateUp)
}
