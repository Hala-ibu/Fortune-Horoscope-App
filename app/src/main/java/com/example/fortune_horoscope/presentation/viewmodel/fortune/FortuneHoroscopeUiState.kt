package com.example.fortune_horoscope.presentation.viewmodel.fortune

import com.example.fortune_horoscope.data.model.Horoscope

data class FortuneHoroscopeUiState(
    val isLoading: Boolean = true,
    val dailyFortune: String = DEFAULT_FORTUNE,
    val horoscopes: List<Horoscope> = emptyList(),
    val errorMessage: String? = null
) {
    companion object {
        const val DEFAULT_FORTUNE = "A bright sign is waiting for you. Draw a fortune to reveal it."
    }
}
