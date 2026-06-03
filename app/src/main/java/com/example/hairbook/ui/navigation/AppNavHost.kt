package com.example.hairbook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hairbook.feature.appointments.ui.AppointmentsRoute
import com.example.hairbook.feature.auth.ui.AuthRoute
import com.example.hairbook.feature.clients.ui.ClientsRoute
import com.example.hairbook.feature.gallery.ui.GalleryRoute
import com.example.hairbook.feature.profile.ui.ProfileRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Auth.route
    ) {
        composable(AppDestination.Auth.route) {
            AuthRoute(onSignedIn = {
                navController.navigate(AppDestination.Clients.route) {
                    popUpTo(AppDestination.Auth.route) { inclusive = true }
                }
            })
        }
        composable(AppDestination.Clients.route) {
            ClientsRoute()
        }
        composable(AppDestination.Appointments.route) {
            AppointmentsRoute()
        }
        composable(AppDestination.Gallery.route) {
            GalleryRoute()
        }
        composable(AppDestination.Profile.route) {
            ProfileRoute()
        }
    }
}
