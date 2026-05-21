package com.example.fortune_horoscope.data.repository

import com.example.fortune_horoscope.data.local.dao.HabitDao
import com.example.fortune_horoscope.data.local.dao.HoroscopeDao
import com.example.fortune_horoscope.data.local.dao.JournalEntryDao
import com.example.fortune_horoscope.data.local.dao.PosterDao
import com.example.fortune_horoscope.data.local.dao.UserDao
import com.example.fortune_horoscope.data.local.dao.ZodiacSignDao
import com.example.fortune_horoscope.data.local.entity.UserEntity
import com.example.fortune_horoscope.data.local.util.DatabaseSeeder
import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.data.model.JournalEntry
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.data.model.User
import com.example.fortune_horoscope.data.repository.mappers.toDomain
import com.example.fortune_horoscope.data.repository.mappers.toEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FortuneRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val zodiacSignDao: ZodiacSignDao,
    private val horoscopeDao: HoroscopeDao,
    private val habitDao: HabitDao,
    private val journalEntryDao: JournalEntryDao,
    private val posterDao: PosterDao,
    private val seeder: DatabaseSeeder
) : FortuneRepository {
    override suspend fun seedStarterData() = seeder.seedIfNeeded()

    override suspend fun register(fullName: String, email: String, password: String): Result<User> = runCatching {
        require(userDao.getByEmail(email) == null) { "Email already registered" }
        val id = userDao.upsert(UserEntity(fullName = fullName, email = email, password = password))
        userDao.getById(id)?.toDomain() ?: error("Unable to create user")
    }

    override suspend fun login(email: String, password: String): Result<User> = runCatching {
        val user = userDao.getByEmail(email) ?: error("Invalid email or password")
        require(user.password == password) { "Invalid email or password" }
        user.toDomain()
    }

    override fun observeZodiacSigns() = zodiacSignDao.observeAll().map { signs -> signs.map { it.toDomain() } }
    override fun observeHoroscopes() = horoscopeDao.observeAll().map { readings -> readings.map { it.toDomain() } }
    override fun observeHabits(userId: Long) = habitDao.observeForUser(userId).map { habits -> habits.map { it.toDomain() } }
    override suspend fun saveHabit(habit: Habit) { habitDao.upsert(habit.toEntity()) }
    override suspend fun deleteHabit(habit: Habit) { habitDao.delete(habit.toEntity()) }
    override fun observeJournalEntries(userId: Long) = journalEntryDao.observeForUser(userId).map { entries -> entries.map { it.toDomain() } }
    override suspend fun saveJournalEntry(entry: JournalEntry) { journalEntryDao.upsert(entry.toEntity()) }
    override suspend fun deleteJournalEntry(entry: JournalEntry) { journalEntryDao.delete(entry.toEntity()) }
    override fun observePosters() = posterDao.observeAll().map { posters -> posters.map { it.toDomain() } }
    override suspend fun savePoster(poster: Poster) { posterDao.upsert(poster.toEntity()) }
    override suspend fun deletePoster(poster: Poster) { posterDao.delete(poster.toEntity()) }
}