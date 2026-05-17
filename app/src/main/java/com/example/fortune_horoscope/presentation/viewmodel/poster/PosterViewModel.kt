package com.example.fortune_horoscope.presentation.viewmodel.poster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PosterViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState<List<Poster>>>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState<List<Poster>>> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading
            repository.observePosters().collect { _uiState.value = ScreenUiState.Success(it) }
        }
    }
}