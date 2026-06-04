package com.example.hairbook.feature.auth.ui

import androidx.compose.runtime.Composable

@Composable
fun AuthRoute(onSignedIn: () -> Unit) {
    AuthScreen(
        onContinue = onSignedIn
    )
}
