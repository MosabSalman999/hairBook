package com.example.hairbook.feature.favourites.ui

import androidx.compose.runtime.Composable

@Composable
fun FavouritesRoute(onNavigateToDetail: () -> Unit = {}) {
    FavouritesScreen(onNavigateToDetail = onNavigateToDetail)
}
