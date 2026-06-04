package com.example.hairbook.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hairbook.R
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.feature.admin.ui.AdminRoute
import com.example.hairbook.feature.auth.ui.AuthRoute
import com.example.hairbook.feature.booking.ui.BookingRoute
import com.example.hairbook.feature.browse.ui.BrowseRoute
import com.example.hairbook.feature.browse.ui.CategoryRoute
import com.example.hairbook.feature.browse.ui.HomeRoute
import com.example.hairbook.feature.detail.ui.DetailRoute
import com.example.hairbook.feature.favourites.ui.FavouritesRoute
import com.example.hairbook.feature.finder.ui.FinderRoute
import com.example.hairbook.feature.profile.ui.ProfileRoute

private val bottomNavDestinations = setOf(
    AppDestination.Home.route,
    AppDestination.Finder.route,
    AppDestination.Favourites.route,
    AppDestination.Profile.route,
)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavDestinations) {
                HorizontalDivider(color = GoldMuted, thickness = 1.dp)
                NavigationBar(
                    containerColor = SurfaceDark,
                    tonalElevation = 0.dp,
                ) {
                    NavigationBarItem(
                        selected = currentRoute == AppDestination.Home.route,
                        onClick = {
                            navController.navigate(AppDestination.Home.route) {
                                popUpTo(AppDestination.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_browse)) },
                        colors = navItemColors(),
                    )
                    NavigationBarItem(
                        selected = currentRoute == AppDestination.Finder.route,
                        onClick = {
                            navController.navigate(AppDestination.Finder.route) {
                                popUpTo(AppDestination.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Search, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_finder)) },
                        colors = navItemColors(),
                    )
                    NavigationBarItem(
                        selected = currentRoute == AppDestination.Favourites.route,
                        onClick = {
                            navController.navigate(AppDestination.Favourites.route) {
                                popUpTo(AppDestination.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_favourites)) },
                        colors = navItemColors(),
                    )
                    NavigationBarItem(
                        selected = currentRoute == AppDestination.Profile.route,
                        onClick = {
                            navController.navigate(AppDestination.Profile.route) {
                                popUpTo(AppDestination.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_profile)) },
                        colors = navItemColors(),
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(AppDestination.Auth.route) {
                AuthRoute(onSignedIn = {
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Auth.route) { inclusive = true }
                    }
                })
            }
            composable(AppDestination.Home.route) {
                HomeRoute(onNavigateToCategory = { categoryId ->
                    navController.navigate(AppDestination.categoryRoute(categoryId))
                })
            }
            composable(
                route = AppDestination.Category.route,
                arguments = listOf(navArgument("categoryId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
                CategoryRoute(
                    categoryId = categoryId,
                    onNavigateToDetail = { navController.navigate(AppDestination.Detail.route) },
                    onNavigateUp = { navController.navigateUp() },
                )
            }
            composable(AppDestination.Browse.route) {
                BrowseRoute(onNavigateToDetail = {
                    navController.navigate(AppDestination.Detail.route)
                })
            }
            composable(AppDestination.Detail.route) {
                DetailRoute(onNavigateUp = { navController.navigateUp() })
            }
            composable(AppDestination.Finder.route) {
                FinderRoute()
            }
            composable(AppDestination.Favourites.route) {
                FavouritesRoute(onNavigateToDetail = {
                    navController.navigate(AppDestination.Detail.route)
                })
            }
            composable(AppDestination.Admin.route) {
                AdminRoute(onNavigateUp = { navController.navigateUp() })
            }
            composable(AppDestination.Booking.route) {
                BookingRoute(onNavigateUp = { navController.navigateUp() })
            }
            composable(AppDestination.Profile.route) {
                ProfileRoute(
                    onNavigateToAuth = {
                        navController.navigate(AppDestination.Auth.route) {
                            popUpTo(AppDestination.Home.route) { inclusive = true }
                        }
                    },
                    onNavigateToAdmin = { navController.navigate(AppDestination.Admin.route) },
                    onNavigateToBooking = { navController.navigate(AppDestination.Booking.route) },
                )
            }
        }
    }
}

@Composable
private fun navItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = Gold,
    selectedTextColor = Gold,
    unselectedIconColor = GreyMuted,
    unselectedTextColor = GreyMuted,
    indicatorColor = SurfaceDark,
)
