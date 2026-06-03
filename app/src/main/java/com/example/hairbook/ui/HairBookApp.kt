package com.example.hairbook.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.hairbook.core.ui.theme.HairBookTheme
import com.example.hairbook.ui.navigation.AppNavHost

@Composable
fun HairBookApp() {
    HairBookTheme {
        Surface {
            AppNavHost()
        }
    }
}
