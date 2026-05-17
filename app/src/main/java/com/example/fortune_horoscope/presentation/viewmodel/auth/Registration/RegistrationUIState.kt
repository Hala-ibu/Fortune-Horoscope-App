package com.example.fortune_horoscope.presentation.viewmodel.auth.Registration

sealed interface RegistrationUIState {
    data object Init : RegistrationUIState
    data object Loading : RegistrationUIState
    data class Success(val isRegistered: Boolean) : RegistrationUIState
    data class Error(val message: String) : RegistrationUIState
}