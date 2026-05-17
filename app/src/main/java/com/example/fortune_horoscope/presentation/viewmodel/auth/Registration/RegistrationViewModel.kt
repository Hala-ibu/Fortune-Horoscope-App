package com.example.fortune_horoscope.presentation.viewmodel.auth.Registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.data.repository.user.SessionRepository
import com.example.fortune_horoscope.presentation.viewmodel.auth.Registration.RegistrationNavigationEvent
import com.example.fortune_horoscope.presentation.viewmodel.auth.Registration.RegistrationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: FortuneRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegistrationUIState>(RegistrationUIState.Init)
    val uiState: StateFlow<RegistrationUIState> = _uiState

    private val _navigationEvent = Channel<RegistrationNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onRegisterClick(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegistrationUIState.Loading
            repository.register(fullName, email, password)
                .onSuccess { user ->
                    sessionRepository.saveSession(user.id) // Save user session ID upon registration
                    _uiState.value = RegistrationUIState.Success(isRegistered = true)
                    _navigationEvent.send(RegistrationNavigationEvent.NavigateHome)
                }
                .onFailure { error ->
                    _uiState.value = RegistrationUIState.Error(error.message ?: "Registration failed")
                }
        }
    }
}