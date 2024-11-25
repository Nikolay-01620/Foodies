package com.foodies.core_ui.route

sealed class Route(
    val route: String
) {
    data object SplashScreen : Route(route = "splashScreen")


    data object CatalogScreen : Route(route = "catalogScreen")

    data object DetailsScreen : Route(route = "detailsScreen")

    data object BasketScreen : Route(route = "basketScreen")

    data object SearchScreen : Route(route = "searchScreen")
}