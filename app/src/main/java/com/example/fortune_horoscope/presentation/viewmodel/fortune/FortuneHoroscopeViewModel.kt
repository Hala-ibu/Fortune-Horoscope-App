package com.example.fortune_horoscope.presentation.viewmodel.fortune

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.remote.repository.NetworkHoroscopeRepository
import com.example.fortune_horoscope.data.repository.FortuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class FortuneHoroscopeViewModel @Inject constructor(
    private val localRepository: FortuneRepository,
    private val networkRepository: NetworkHoroscopeRepository
) : ViewModel() {
    private val fortunes = listOf(
        "Today rewards the brave choice you have been postponing.",
        "A calm conversation will unlock an unexpected opportunity.",
        "Trust your intuition; it already noticed what your mind is still naming.",
        "Small rituals bring big clarity when you repeat them with care.",
        "Your lucky moment arrives when you choose patience over pressure.",
        "A creative spark is near. Give it ten quiet minutes and it will grow."
    )

    private val _uiState = MutableStateFlow(FortuneHoroscopeUiState())
    val uiState: StateFlow<FortuneHoroscopeUiState> = _uiState.asStateFlow()

    init {
        observeSavedHoroscopes()
        refreshHoroscopes()
    }

    fun drawFortune() {
        val currentFortune = _uiState.value.dailyFortune
        val nextFortune = fortunes
            .filterNot { it == currentFortune }
            .randomOrNull()
            ?: fortunes.random()

        _uiState.value = _uiState.value.copy(dailyFortune = nextFortune)
    }

    fun refreshHoroscopes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            localRepository.seedStarterData()
            networkRepository.fetchDailyHoroscopes()
                .onSuccess { remoteHoroscopes ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        horoscopes = remoteHoroscopes,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Unable to load fresh horoscopes. Showing saved readings instead."
                    )
                }
        }
    }

    private fun observeSavedHoroscopes() {
        viewModelScope.launch {
            localRepository.observeHoroscopes()
                .catch { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Unable to load saved horoscopes."
                    )
                }
                .collect { savedHoroscopes ->
                    if (_uiState.value.horoscopes.isEmpty()) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            horoscopes = savedHoroscopes
                        )
                    }
                }
        }
    }
}
