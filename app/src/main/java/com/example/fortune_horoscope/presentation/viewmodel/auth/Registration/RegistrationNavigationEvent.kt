package com.example.fortune_horoscope.presentation.viewmodel.auth.Registration

sealed interface RegistrationNavigationEvent {
    data object NavigateHome : RegistrationNavigationEvent
    data object NavigateLogin : RegistrationNavigationEvent
}