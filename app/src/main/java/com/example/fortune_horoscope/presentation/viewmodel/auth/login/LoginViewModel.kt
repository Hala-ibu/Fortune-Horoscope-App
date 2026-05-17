package com.example.fortune_horoscope.presentation.viewmodel.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.repository.FortuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.fortune_horoscope.domain.repository.FortuneRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _navigationEvent = Channel<LoginNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            repository.seedStarterData()
            repository.login(email, password)
                .onSuccess {
                    _uiState.value = LoginUiState.Success(isLoggedIn = true)
                    _navigationEvent.send(LoginNavigationEvent.Navigate)
                }
                .onFailure { error ->
                    _uiState.value = LoginUiState.Error(error.message ?: "Invalid email or password")
                }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}