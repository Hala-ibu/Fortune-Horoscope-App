package com.example.fortune_horoscope.data.remote.repository

import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.data.remote.api.HoroscopeApiService
import com.example.fortune_horoscope.data.remote.dto.toDomain
import com.example.fortune_horoscope.data.remote.dto.toRequestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitHoroscopeRepository @Inject constructor(
    private val apiService: HoroscopeApiService
) : NetworkHoroscopeRepository {
    override suspend fun fetchDailyHoroscopes(): Result<List<Horoscope>> = runCatching {
        apiService.getHoroscopes()
            .take(12)
            .map { it.toDomain() }
    }

    override suspend fun createHoroscope(horoscope: Horoscope): Result<Horoscope> = runCatching {
        apiService.createHoroscope(horoscope.toRequestDto()).toDomain()
    }

    override suspend fun updateHoroscope(horoscope: Horoscope): Result<Horoscope> = runCatching {
        apiService.updateHoroscope(horoscope.id, horoscope.toRequestDto()).toDomain()
    }

    override suspend fun deleteHoroscope(id: Long): Result<Unit> = runCatching {
        apiService.deleteHoroscope(id)
    }
}
