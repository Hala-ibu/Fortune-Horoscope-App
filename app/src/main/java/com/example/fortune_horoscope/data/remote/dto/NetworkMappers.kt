package com.example.fortune_horoscope.data.remote.dto

import com.example.fortune_horoscope.data.model.Horoscope
import kotlin.math.absoluteValue

fun HoroscopeDto.toDomain(): Horoscope {
    val stableId = id ?: title.hashCode().toLong().absoluteValue
    return Horoscope(
        id = stableId,
        zodiacSignId = zodiacSignId,
        title = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
        message = message.replace('\n', ' '),
        mood = listOf("Aligned", "Inspired", "Grounded", "Brave", "Calm")[(stableId % 5).toInt()],
        luckyNumber = ((stableId % 9) + 1).toInt()
    )
}

fun Horoscope.toRequestDto(): HoroscopeRequestDto = HoroscopeRequestDto(
    zodiacSignId = zodiacSignId,
    title = title,
    message = message
)
