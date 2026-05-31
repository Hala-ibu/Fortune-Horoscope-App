package com.example.fortune_horoscope.data.di
import com.example.fortune_horoscope.data.repository.user.SessionRepository
import com.example.fortune_horoscope.data.repository.user.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.fortune_horoscope.data.repository.FortuneRepository
import com.example.fortune_horoscope.data.repository.FortuneRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFortuneRepository(
        fortuneRepositoryImpl: FortuneRepositoryImpl
    ): FortuneRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository
}