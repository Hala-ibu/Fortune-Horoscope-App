package com.example.fortune_horoscope.data.repository

import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.data.model.JournalEntry
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.data.model.User
import com.example.fortune_horoscope.data.model.ZodiacSign
import kotlinx.coroutines.flow.Flow

interface FortuneRepository {
    suspend fun seedStarterData()
    suspend fun register(fullName: String, email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    fun observeZodiacSigns(): Flow<List<ZodiacSign>>
    fun observeHoroscopes(): Flow<List<Horoscope>>
    fun observeHabits(userId: Long): Flow<List<Habit>>
    suspend fun saveHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    fun observeJournalEntries(userId: Long): Flow<List<JournalEntry>>
    suspend fun saveJournalEntry(entry: JournalEntry)
    suspend fun deleteJournalEntry(entry: JournalEntry)
    fun observePosters(): Flow<List<Poster>>
    suspend fun savePoster(poster: Poster)
    suspend fun deletePoster(poster: Poster)
}