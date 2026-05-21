package com.example.fortune_horoscope.data.di

import android.content.Context
import androidx.room.Room
import com.example.fortune_horoscope.data.local.dao.HabitDao
import com.example.fortune_horoscope.data.local.dao.HoroscopeDao
import com.example.fortune_horoscope.data.local.dao.JournalEntryDao
import com.example.fortune_horoscope.data.local.dao.PosterDao
import com.example.fortune_horoscope.data.local.dao.UserDao
import com.example.fortune_horoscope.data.local.dao.ZodiacSignDao
import com.example.fortune_horoscope.data.model.local.db.FortuneDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FortuneDatabase =
        Room.databaseBuilder(context, FortuneDatabase::class.java, "fortune_horoscope.db")
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides fun provideUserDao(database: FortuneDatabase): UserDao = database.userDao()
    @Provides fun provideZodiacSignDao(database: FortuneDatabase): ZodiacSignDao = database.zodiacSignDao()
    @Provides fun provideHoroscopeDao(database: FortuneDatabase): HoroscopeDao = database.horoscopeDao()
    @Provides fun provideHabitDao(database: FortuneDatabase): HabitDao = database.habitDao()
    @Provides fun provideJournalEntryDao(database: FortuneDatabase): JournalEntryDao = database.journalEntryDao()
    @Provides fun providePosterDao(database: FortuneDatabase): PosterDao = database.posterDao()
}
