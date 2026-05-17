package com.example.fortune_horoscope.presentation.viewmodel.journal
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.JournalEntry
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.data.repository.user.SessionRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val repository: FortuneRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ScreenUiState<List<JournalEntry>>> = sessionRepository.currentUserId
        .flatMapLatest { userId ->
            if (userId == null) {
                flowOf(ScreenUiState.Error("No active user session found"))
            } else {
                repository.observeJournalEntries(userId)
                    .map { ScreenUiState.Success(it) as ScreenUiState<List<JournalEntry>> }
                    .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load entries")) }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )

    fun saveEntry(title: String, body: String) = viewModelScope.launch {
        if (title.isBlank() || body.isBlank()) return@launch
        val userId = sessionRepository.currentUserId.value ?: return@launch
        repository.saveJournalEntry(
            JournalEntry(0, userId, title.trim(), body.trim(), System.currentTimeMillis())
        )
    }
}