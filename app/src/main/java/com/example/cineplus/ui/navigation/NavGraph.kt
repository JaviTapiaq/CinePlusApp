package com.example.cineplus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.cineplus.ui.screens.*
import com.example.cineplus.viewmodel.ProfileViewModel
import com.example.cineplus.viewmodel.ProfileViewModelFactory
import com.example.cineplus.repository.AuthRepository

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")

    object Movies : Screen("movies")

}

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(Screen.Start.route) {
            StartScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Home.route) },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onMoviesClick = { navController.navigate(Screen.Movies.route) }
            )
        }

        composable(Screen.Movies.route) {
            MoviesScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Profile.route) {
            val repo = AuthRepository(context)

            val profileViewModel: ProfileViewModel = viewModel(
                factory = ProfileViewModelFactory(repo)
            )

            ProfileScreen(
                navController = navController,
                viewModel = profileViewModel
            )
        }
    }
}


