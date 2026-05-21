package com.example.fortune_horoscope.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            runCatching { repository.seedStarterData() }
        }
    }

    val uiState: StateFlow<ScreenUiState<List<Horoscope>>> = repository.observeHoroscopes()
        .map { ScreenUiState.Success(it) as ScreenUiState<List<Horoscope>> }
        .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load horoscopes")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )
}