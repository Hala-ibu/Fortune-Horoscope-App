package com.example.fortune_horoscope.presentation.viewmodel.auth.login

sealed interface LoginNavigationEvent {
    data object Navigate : LoginNavigationEvent
    data object NavigateBack : LoginNavigationEvent
}