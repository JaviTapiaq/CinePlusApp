package com.example.cineplus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cineplus.ui.screens.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { MovieListScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("detail/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieDetailScreen(navController, id)
        }
        composable("purchase/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieId") ?: ""
            PurchaseScreen(navController, id)
        }
        composable("player/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.StringType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieId") ?: ""
            PlayerScreen(navController, id)
        }
        composable("profile") { ProfileScreen(navController) }
    }
}
