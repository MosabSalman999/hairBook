package com.example.hairbook.ui.navigation

enum class AppDestination(val route: String) {
    Auth("auth"),
    Home("home"),
    Category("category/{categoryId}"),
    Browse("browse"),
    Detail("detail"),
    Finder("finder"),
    Favourites("favourites"),
    Admin("admin"),
    Booking("booking"),
    Profile("profile");

    companion object {
        fun categoryRoute(categoryId: String) = "category/$categoryId"
    }
}
