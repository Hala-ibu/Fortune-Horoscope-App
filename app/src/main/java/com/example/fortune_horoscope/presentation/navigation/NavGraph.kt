package com.example.fortune_horoscope.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fortune_horoscope.presentation.ui.screens.ArtPoster.PosterSigns
import com.example.fortune_horoscope.presentation.ui.screens.Explorer.ExploreScreen
import com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker.RitualsScreen
import com.example.fortune_horoscope.presentation.ui.screens.Home.HomeScreen
import com.example.fortune_horoscope.presentation.ui.screens.Login.LoginScreen
import com.example.fortune_horoscope.presentation.ui.screens.Registration.RegistrationScreen
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.ZodiacDetailsScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Registration.route) {
            RegistrationScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Registration.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Registration.route)
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                onExploreClick = {
                    navController.navigate(Screen.Explore.route)
                }
            )
        }

        composable(route = Screen.Explore.route) {
            ExploreScreen()
        }

        composable(route = Screen.ZodiacDetails.route) {
            ZodiacDetailsScreen()
        }

        composable(route = Screen.HabitTracker.route) {
            RitualsScreen()
        }

        composable(route = Screen.ArtPoster.route) {
            PosterSigns()
        }
    }
}