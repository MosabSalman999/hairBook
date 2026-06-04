package com.example.hairbook.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val HairBookColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Black,
    primaryContainer = GoldAlpha24,
    onPrimaryContainer = GoldLight,
    background = Black,
    onBackground = White,
    surface = SurfaceDark,
    onSurface = White,
    surfaceVariant = SurfaceElevated,
    onSurfaceVariant = GreyLight,
    error = Error,
    onError = White,
    outline = GoldMuted,
    outlineVariant = GreyMuted,
)

@Composable
fun HairBookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HairBookColorScheme,
        typography = HairBookTypography,
        shapes = HairBookShapes,
        content = content,
    )
}
