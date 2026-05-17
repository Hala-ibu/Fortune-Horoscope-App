package com.example.fortune_horoscope.presentation.viewmodel.auth.Registration


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.domain.repository.FortuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegistrationUIState>(RegistrationUIState.Init)
    val uiState: StateFlow<RegistrationUIState> = _uiState

    private val _navigationEvent = Channel<RegistrationNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onRegisterClick(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegistrationUIState.Loading
            repository.register(fullName, email, password)
                .onSuccess {
                    _uiState.value = RegistrationUIState.Success(isRegistered = true)
                    _navigationEvent.send(RegistrationNavigationEvent.NavigateHome)
                }
                .onFailure { error ->
                    _uiState.value = RegistrationUIState.Error(error.message ?: "Registration failed")
                }
        }
    }
}