package com.example.fortune_horoscope.presentation.viewmodel.Zodiac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.ZodiacSign
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class ZodiacDetailsViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            runCatching { repository.seedStarterData() }
        }
    }

    val uiState: StateFlow<ScreenUiState<ZodiacSign?>> = repository.observeZodiacSigns()
        .map { signs -> ScreenUiState.Success(signs.firstOrNull()) as ScreenUiState<ZodiacSign?> }
        .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load zodiac details")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )
}