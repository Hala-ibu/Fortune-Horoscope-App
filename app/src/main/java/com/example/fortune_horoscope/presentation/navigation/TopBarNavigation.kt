package com.example.fortune_horoscope.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentRoute: String?,
    onBackClick: () -> Unit
) {
    val topLevelRoutes = setOf(
        Screen.Home.route)

    val isTopLevel = currentRoute in topLevelRoutes

    TopAppBar(
        title = {
            Text(
                text = when (currentRoute) {
                    Screen.Home.route -> "Home"
                    Screen.HabitTracker.route -> "Habit Tracker"
                    Screen.Explore.route -> "Explore"
                    Screen.ArtPoster.route -> "Art Gallery"
                    else -> "Details"
                }
            )
        },
        navigationIcon = {
            if (!isTopLevel && currentRoute != Screen.Login.route && currentRoute != Screen.Registration.route) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}