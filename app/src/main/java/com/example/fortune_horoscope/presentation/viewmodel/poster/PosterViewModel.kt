package com.example.fortune_horoscope.presentation.viewmodel.poster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class PosterViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            runCatching { repository.seedStarterData() }
        }
    }

    val uiState: StateFlow<ScreenUiState<List<Poster>>> = repository.observePosters()
        .map { ScreenUiState.Success(it) as ScreenUiState<List<Poster>> }
        .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load posters")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )

    fun addPoster(title: String, paletteName: String) = viewModelScope.launch {
        if (title.isBlank() || paletteName.isBlank()) return@launch
        runCatching {
            repository.savePoster(
                Poster(
                    id = 0,
                    zodiacSignId = 1L, // Set dynamic default sign mapping index
                    title = title.trim(),
                    paletteName = paletteName.trim(),
                    downloaded = false
                )
            )
        }
    }

    fun toggleDownloaded(poster: Poster) = viewModelScope.launch {
        runCatching { repository.savePoster(poster.copy(downloaded = !poster.downloaded)) }
    }

    fun deletePoster(poster: Poster) = viewModelScope.launch {
        runCatching { repository.deletePoster(poster) }
    }
}