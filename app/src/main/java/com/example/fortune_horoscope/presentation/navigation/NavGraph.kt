package com.example.fortune_horoscope.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fortune_horoscope.presentation.ui.screens.ArtPoster.PosterSigns
import com.example.fortune_horoscope.presentation.ui.screens.Explorer.ExploreScreen
import com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker.RitualsScreen
import com.example.fortune_horoscope.presentation.ui.screens.Home.HomeScreen
import com.example.fortune_horoscope.presentation.ui.screens.Login.LoginScreen
import com.example.fortune_horoscope.presentation.ui.screens.Registration.RegistrationScreen
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.ZodiacDetailsScreen
import com.example.fortune_horoscope.presentation.viewmodel.auth.Registration.RegistrationNavigationEvent
import com.example.fortune_horoscope.presentation.viewmodel.auth.Registration.RegistrationViewModel
import com.example.fortune_horoscope.presentation.viewmodel.auth.login.LoginNavigationEvent
import com.example.fortune_horoscope.presentation.viewmodel.auth.login.LoginViewModel
import com.example.fortune_horoscope.presentation.viewmodel.explore.ExploreViewModel
import com.example.fortune_horoscope.presentation.viewmodel.habit.HabitTrackerViewModel
import com.example.fortune_horoscope.presentation.viewmodel.home.HomeViewModel
import com.example.fortune_horoscope.presentation.viewmodel.poster.PosterViewModel
import com.example.fortune_horoscope.presentation.viewmodel.Zodiac.ZodiacDetailsViewModel

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
            val viewModel: RegistrationViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(viewModel) {
                viewModel.navigationEvent.collect { event ->
                    when (event) {
                        RegistrationNavigationEvent.NavigateHome -> navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Registration.route) { inclusive = true }
                        }
                        RegistrationNavigationEvent.NavigateLogin -> navController.navigate(Screen.Login.route)
                    }
                }
            }

            RegistrationScreen(
                uiState = uiState,
                onRegisterClick = viewModel::onRegisterClick,
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(route = Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(viewModel) {
                viewModel.navigationEvent.collect { event ->
                    when (event) {
                        LoginNavigationEvent.Navigate -> navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                        LoginNavigationEvent.NavigateBack -> navController.popBackStack()
                    }
                }
            }

            LoginScreen(
                uiState = uiState,
                onLoginClick = viewModel::onLoginClick,
                onNavigateToRegister = { navController.navigate(Screen.Registration.route) }
            )
        }

        composable(route = Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                uiState = uiState,
                onExploreClick = { navController.navigate(Screen.Explore.route) }
            )
        }

        composable(route = Screen.Explore.route) {
            val viewModel: ExploreViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            ExploreScreen(uiState = uiState)
        }

        composable(route = Screen.ZodiacDetails.route) {
            val viewModel: ZodiacDetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            ZodiacDetailsScreen(uiState = uiState)
        }

        composable(route = Screen.HabitTracker.route) {
            val viewModel: HabitTrackerViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            RitualsScreen(
                uiState = uiState,
                onAddHabit = { title, category -> viewModel.addHabit(title, category) },
                onToggleComplete = { habit -> viewModel.toggleComplete(habit) },
                onDeleteHabit = { habit -> viewModel.deleteHabit(habit) }
            )
        }

        composable(route = Screen.ArtPoster.route) {
            val viewModel: PosterViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            PosterSigns(
                uiState = uiState,
                onAddPoster = { title, palette -> viewModel.addPoster(title, palette) },
                onToggleDownloaded = { poster -> viewModel.toggleDownloaded(poster) },
                onDeletePoster = { poster -> viewModel.deletePoster(poster) }
            )
        }
    }
}