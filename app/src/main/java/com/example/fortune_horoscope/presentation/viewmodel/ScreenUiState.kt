package com.example.fortune_horoscope.presentation.viewmodel

sealed interface ScreenUiState<out T> {
    data object Init : ScreenUiState<Nothing>
    data object Loading : ScreenUiState<Nothing>
    data class Success<T>(val data: T) : ScreenUiState<T>
    data class Error(val message: String) : ScreenUiState<Nothing>
}