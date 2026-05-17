package com.example.fortune_horoscope.presentation.viewmodel.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val repository: FortuneRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState<List<Habit>>>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState<List<Habit>>> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading
            repository.seedStarterData()
            repository.observeHabits(DEMO_USER_ID).collect { _uiState.value = ScreenUiState.Success(it) }
        }
    }

    fun toggleComplete(habit: Habit) = viewModelScope.launch {
        repository.saveHabit(habit.copy(completedToday = !habit.completedToday))
    }

    companion object { private const val DEMO_USER_ID = 1L }
}