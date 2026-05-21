package com.example.fortune_horoscope.data.repository.user

import kotlinx.coroutines.flow.StateFlow

interface SessionRepository {
    val currentUserId: StateFlow<Long?>
    suspend fun saveSession(userId: Long)
    suspend fun clearSession()
}