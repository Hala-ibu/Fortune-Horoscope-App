package com.example.fortune_horoscope.data.remote.repository

import com.example.fortune_horoscope.data.model.Horoscope

interface NetworkHoroscopeRepository {
    suspend fun fetchDailyHoroscopes(): Result<List<Horoscope>>
    suspend fun createHoroscope(horoscope: Horoscope): Result<Horoscope>
    suspend fun updateHoroscope(horoscope: Horoscope): Result<Horoscope>
    suspend fun deleteHoroscope(id: Long): Result<Unit>
}
