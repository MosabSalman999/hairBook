package com.example.hairbook.feature.browse.ui

import androidx.compose.runtime.Composable

@Composable
fun BrowseRoute(onNavigateToDetail: () -> Unit = {}) {
    BrowseScreen(onNavigateToDetail = onNavigateToDetail)
}
