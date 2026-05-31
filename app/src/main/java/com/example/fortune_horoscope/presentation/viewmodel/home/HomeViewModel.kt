package com.example.fortune_horoscope.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.data.remote.repository.NetworkHoroscopeRepository
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FortuneRepository,
    private val networkRepository: NetworkHoroscopeRepository
) : ViewModel() {
    private val remoteRefreshState = MutableStateFlow<ScreenUiState<List<Horoscope>>>(ScreenUiState.Loading)

    val uiState: StateFlow<ScreenUiState<List<Horoscope>>> = combine(
        repository.observeHoroscopes(),
        remoteRefreshState
    ) { localHoroscopes, remoteState ->
        when {
            remoteState is ScreenUiState.Success -> remoteState
            localHoroscopes.isNotEmpty() -> ScreenUiState.Success(localHoroscopes)
            else -> remoteState
        }
    }
        .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load horoscopes")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )

    init {
        viewModelScope.launch {
            runCatching { repository.seedStarterData() }
            refreshHoroscopes()
        }
    }

    fun refreshHoroscopes() {
        viewModelScope.launch {
            remoteRefreshState.value = ScreenUiState.Loading
            networkRepository.fetchDailyHoroscopes()
                .onSuccess { horoscopes ->
                    remoteRefreshState.value = ScreenUiState.Success(horoscopes)
                }
                .onFailure { error ->
                    remoteRefreshState.value = ScreenUiState.Error(
                        error.message ?: "Unable to refresh horoscopes from the network"
                    )
                }
        }
    }
}