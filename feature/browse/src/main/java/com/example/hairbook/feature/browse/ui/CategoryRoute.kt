package com.example.hairbook.feature.browse.ui

import androidx.compose.runtime.Composable

@Composable
fun CategoryRoute(
    categoryId: String,
    onNavigateToDetail: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    CategoryScreen(
        categoryId = categoryId,
        onNavigateToDetail = onNavigateToDetail,
        onNavigateUp = onNavigateUp,
    )
}
