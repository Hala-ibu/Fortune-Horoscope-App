package com.example.fortune_horoscope.di

import com.example.fortune_horoscope.data.repository.FortuneRepositoryImpl
import com.example.fortune_horoscope.data.repository.FortuneRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFortuneRepository(implementation: FortuneRepositoryImpl): FortuneRepository
}