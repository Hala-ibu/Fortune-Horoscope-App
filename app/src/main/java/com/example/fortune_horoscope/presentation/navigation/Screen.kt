package com.example.fortune_horoscope.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Registration : Screen("Registration")
    data object Login : Screen("Login")
    data object Home : Screen("Home")
    data object HabitTracker : Screen("HabitTracker")
    data object Explore : Screen("Explore")
    data object ArtPoster :Screen("ArtPoster")
    data object ZodiacDetails : Screen("ZodiacDetails/{sign}/{date}")
}
