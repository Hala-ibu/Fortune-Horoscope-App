package com.example.fortune_horoscope.data.repository.user

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepositoryImpl @Inject constructor() : SessionRepository {
    private val _currentUserId = MutableStateFlow<Long?>(null)
    override val currentUserId: StateFlow<Long?> = _currentUserId.asStateFlow()

    override suspend fun saveSession(userId: Long) {
        _currentUserId.value = userId
    }

    override suspend fun clearSession() {
        _currentUserId.value = null
    }
}