package com.example.fortune_horoscope.presentation.viewmodel.journal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.JournalEntry
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState<List<JournalEntry>>>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState<List<JournalEntry>>> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading
            repository.observeJournalEntries(DEMO_USER_ID).collect { _uiState.value = ScreenUiState.Success(it) }
        }
    }

    fun saveEntry(title: String, body: String) = viewModelScope.launch {
        repository.saveJournalEntry(JournalEntry(0, DEMO_USER_ID, title, body, System.currentTimeMillis()))
    }

    companion object { private const val DEMO_USER_ID = 1L }
}