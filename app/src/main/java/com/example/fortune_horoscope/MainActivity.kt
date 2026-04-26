package com.example.fortune_horoscope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fortune_horoscope.presentation.navigation.AppTopBar
import com.example.fortune_horoscope.presentation.navigation.BottomBarNavigationComponent
import com.example.fortune_horoscope.presentation.navigation.BottomBarNavigationItems.items
import com.example.fortune_horoscope.presentation.navigation.NavGraph
import com.example.fortune_horoscope.presentation.navigation.Screen
import com.example.fortune_horoscope.presentation.theme.FortunehoroscopeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FortunehoroscopeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                Scaffold(
                    topBar = {
                        if (currentRoute != Screen.Login.route && currentRoute != Screen.Registration.route) {
                            AppTopBar(
                                currentRoute = currentRoute,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    },
                    bottomBar = {
                        if (currentRoute != Screen.Login.route && currentRoute != Screen.Registration.route) {
                            BottomBarNavigationComponent(
                                items = items,
                                selectedItemIndex = items.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0),
                                onItemSelected = { index ->
                                    navController.navigate(items[index].route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = Screen.Login.route
                    )
                }
            }
        }
    }
}