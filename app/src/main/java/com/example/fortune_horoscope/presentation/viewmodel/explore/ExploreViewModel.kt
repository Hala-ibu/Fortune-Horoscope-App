package com.example.fortune_horoscope.presentation.viewmodel.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.ZodiacSign
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState<List<ZodiacSign>>>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState<List<ZodiacSign>>> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading
            repository.seedStarterData()
            repository.observeZodiacSigns().collect { _uiState.value = ScreenUiState.Success(it) }
        }
    }
}