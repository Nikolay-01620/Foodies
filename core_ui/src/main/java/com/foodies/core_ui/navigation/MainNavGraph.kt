package com.foodies.core_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    //catalogViewModel: CatalogViewModel,
   // basketViewModel: BasketViewModel,
    //searchViewModel: SearchViewModel,
) {

    NavHost(navController = navController, startDestination = Route.SplashScreen.route) {

        composable(Route.SplashScreen.route) {
           //SplashScreen(navController = navController)
        }
        composable(Route.CatalogScreen.route) {
           /* CatalogScreen(
                navController = navController,
                catalogViewModel = catalogViewModel,
                basketViewModel = basketViewModel
            )*/

        }
        composable(
            route = Route.DetailsScreen.route + "/{product}",
            arguments = listOf(navArgument(name = "product") {
                type = NavType.StringType
            })
        ) {
           // ProductDetailsScreen(navController = navController, basketViewModel = basketViewModel)
        }
        composable(Route.BasketScreen.route) {
            //BasketScreen(navController = navController, viewModel = basketViewModel)
        }
        composable(Route.SearchScreen.route) {
           /* SearchScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                catalogViewModel = catalogViewModel,
            )*/
        }

    }

}


