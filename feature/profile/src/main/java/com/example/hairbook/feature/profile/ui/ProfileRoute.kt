package com.example.hairbook.feature.profile.ui

import androidx.compose.runtime.Composable

@Composable
fun ProfileRoute(
    onNavigateToAuth: () -> Unit = {},
    onNavigateToAdmin: () -> Unit = {},
    onNavigateToBooking: () -> Unit = {},
) {
    ProfileScreen(
        onNavigateToAuth = onNavigateToAuth,
        onNavigateToAdmin = onNavigateToAdmin,
        onNavigateToBooking = onNavigateToBooking,
    )
}
