package com.example.fortune_horoscope.presentation.viewmodel.habit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.data.repository.user.SessionRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val repository: FortuneRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            runCatching { repository.seedStarterData() }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ScreenUiState<List<Habit>>> = sessionRepository.currentUserId
        .flatMapLatest { userId ->
            if (userId == null) {
                flowOf(ScreenUiState.Error("No active user session found"))
            } else {
                repository.observeHabits(userId)
                    .map { ScreenUiState.Success(it) as ScreenUiState<List<Habit>> }
                    .catch { emit(ScreenUiState.Error(it.message ?: "Unable to load habits")) }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ScreenUiState.Loading
        )

    fun addHabit(title: String, category: String) = viewModelScope.launch {
        if (title.isBlank() || category.isBlank()) return@launch
        val userId = sessionRepository.currentUserId.value ?: return@launch
        runCatching {
            repository.saveHabit(
                Habit(
                    id = 0,
                    userId = userId,
                    title = title.trim(),
                    category = category.trim(),
                    streak = 0,
                    completedToday = false
                )
            )
        }
    }

    fun toggleComplete(habit: Habit) = viewModelScope.launch {
        runCatching { repository.saveHabit(habit.copy(completedToday = !habit.completedToday)) }
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        runCatching { repository.deleteHabit(habit) }
    }
}