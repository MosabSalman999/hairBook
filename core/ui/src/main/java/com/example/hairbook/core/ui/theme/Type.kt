package com.example.hairbook.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Stand-ins until DM Serif Display / DM Sans font files are added (Phase 0 pending).
private val DmSerifDisplay = FontFamily.Serif
private val DmSans = FontFamily.SansSerif

val HairBookTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = DmSerifDisplay,
        fontSize = 48.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 56.sp,
        letterSpacing = (-0.5).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = DmSerifDisplay,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = DmSerifDisplay,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = DmSerifDisplay,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 30.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = DmSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = DmSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = DmSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = DmSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 26.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = DmSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = DmSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = DmSans,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = DmSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = DmSans,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp,
        letterSpacing = 0.8.sp,
    ),
)
