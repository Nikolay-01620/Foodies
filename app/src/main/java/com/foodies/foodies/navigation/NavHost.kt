package com.foodies.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.foodies.basket_feature.presentation.BasketScreen
import com.foodies.catalog_feature.presentation.CatalogScreen
import com.foodies.core_ui.route.Route
import com.foodies.details_feature.presentation.DetailsScreen
import com.foodies.search_feature.presentation.SearchScreen
import com.foodies.splash_feature.presentation.SplashScreen

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    viewModelProvider: ViewModelProvider.Factory
) {

    NavHost(navController = navController, startDestination = Route.SplashScreen.route) {

        composable(Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Route.CatalogScreen.route) {
            CatalogScreen(
                navController = navController,
                viewModelProvider = viewModelProvider
            )

        }
        composable(
            route = Route.DetailsScreen.route + "/{product}",
            arguments = listOf(navArgument(name = "product") {
                type = NavType.StringType
            })
        ) {
            DetailsScreen(
                navController = navController,
                viewModelProvider = viewModelProvider
            )
        }
        composable(Route.BasketScreen.route) {
            BasketScreen(
                navController = navController,
                viewModelProvider = viewModelProvider
            )
        }
        composable(Route.SearchScreen.route) {
            SearchScreen(
                navController = navController,
                viewModelProvider = viewModelProvider
            )
        }
    }
}


