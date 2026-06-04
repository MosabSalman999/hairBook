package com.example.hairbook.feature.browse.ui

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(onNavigateToCategory: (categoryId: String) -> Unit = {}) {
    HomeScreen(onNavigateToCategory = onNavigateToCategory)
}
