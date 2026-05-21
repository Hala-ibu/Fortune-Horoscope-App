package com.example.fortune_horoscope.data.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fortune_horoscope.data.local.dao.HabitDao
import com.example.fortune_horoscope.data.local.dao.HoroscopeDao
import com.example.fortune_horoscope.data.local.dao.JournalEntryDao
import com.example.fortune_horoscope.data.local.dao.PosterDao
import com.example.fortune_horoscope.data.local.dao.UserDao
import com.example.fortune_horoscope.data.local.dao.ZodiacSignDao
import com.example.fortune_horoscope.data.local.entity.HabitEntity
import com.example.fortune_horoscope.data.local.entity.HoroscopeEntity
import com.example.fortune_horoscope.data.local.entity.JournalEntryEntity
import com.example.fortune_horoscope.data.local.entity.PosterEntity
import com.example.fortune_horoscope.data.local.entity.UserEntity
import com.example.fortune_horoscope.data.local.entity.ZodiacSignEntity

@Database(
    entities = [
        UserEntity::class,
        ZodiacSignEntity::class,
        HoroscopeEntity::class,
        HabitEntity::class,
        JournalEntryEntity::class,
        PosterEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FortuneDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun zodiacSignDao(): ZodiacSignDao
    abstract fun horoscopeDao(): HoroscopeDao
    abstract fun habitDao(): HabitDao
    abstract fun journalEntryDao(): JournalEntryDao
    abstract fun posterDao(): PosterDao
}