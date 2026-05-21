package com.example.fortune_horoscope.data.repository.mappers

import com.example.fortune_horoscope.data.local.entity.HabitEntity
import com.example.fortune_horoscope.data.local.entity.HoroscopeEntity
import com.example.fortune_horoscope.data.local.entity.JournalEntryEntity
import com.example.fortune_horoscope.data.local.entity.PosterEntity
import com.example.fortune_horoscope.data.local.entity.UserEntity
import com.example.fortune_horoscope.data.local.entity.ZodiacSignEntity
import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.data.model.JournalEntry
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.data.model.User
import com.example.fortune_horoscope.data.model.ZodiacSign

fun UserEntity.toDomain() = User(id, fullName, email, zodiacSignId)
fun ZodiacSignEntity.toDomain() = ZodiacSign(id, name, system, dateRange, ruler, element, description)
fun HoroscopeEntity.toDomain() = Horoscope(id, zodiacSignId, title, message, mood, luckyNumber)
fun HabitEntity.toDomain() = Habit(id, userId, title, category, streak, completedToday)
fun JournalEntryEntity.toDomain() = JournalEntry(id, userId, title, body, createdAt)
fun PosterEntity.toDomain() = Poster(id, zodiacSignId, title, paletteName, downloaded)

fun Habit.toEntity() = HabitEntity(id, userId, title, category, streak, completedToday)
fun JournalEntry.toEntity() = JournalEntryEntity(id, userId, title, body, createdAt)
fun Poster.toEntity() = PosterEntity(id, zodiacSignId, title, paletteName, downloaded)
