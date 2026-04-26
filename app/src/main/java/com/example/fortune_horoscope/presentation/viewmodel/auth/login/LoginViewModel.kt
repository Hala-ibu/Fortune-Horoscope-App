package com.example.fortune_horoscope.presentation.viewmodel.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _navigationEvent = Channel<LoginNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            if (email == "halakablaoui@gmail.com" && password == "14052007") {
                _uiState.value = LoginUiState.Success(isLoggedIn = true)
                _navigationEvent.send(LoginNavigationEvent.Navigate)
            } else {
                _uiState.value = LoginUiState.Error("Invalid email or password")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}