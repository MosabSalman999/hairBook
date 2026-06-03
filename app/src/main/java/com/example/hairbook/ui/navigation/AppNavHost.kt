package com.example.hairbook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hairbook.feature.admin.ui.AdminRoute
import com.example.hairbook.feature.auth.ui.AuthRoute
import com.example.hairbook.feature.booking.ui.BookingRoute
import com.example.hairbook.feature.browse.ui.BrowseRoute
import com.example.hairbook.feature.detail.ui.DetailRoute
import com.example.hairbook.feature.favourites.ui.FavouritesRoute
import com.example.hairbook.feature.finder.ui.FinderRoute
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
                navController.navigate(AppDestination.Browse.route) {
                    popUpTo(AppDestination.Auth.route) { inclusive = true }
                }
            })
        }
        composable(AppDestination.Browse.route) {
            BrowseRoute()
        }
        composable(AppDestination.Detail.route) {
            DetailRoute()
        }
        composable(AppDestination.Finder.route) {
            FinderRoute()
        }
        composable(AppDestination.Favourites.route) {
            FavouritesRoute()
        }
        composable(AppDestination.Admin.route) {
            AdminRoute()
        }
        composable(AppDestination.Booking.route) {
            BookingRoute()
        }
        composable(AppDestination.Profile.route) {
            ProfileRoute()
        }
    }
}
