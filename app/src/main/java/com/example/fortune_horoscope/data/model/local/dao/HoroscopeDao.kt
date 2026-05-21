package com.example.fortune_horoscope.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fortune_horoscope.data.local.entity.HoroscopeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HoroscopeDao {
    @Query("SELECT * FROM horoscopes ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<HoroscopeEntity>>

    @Query("SELECT * FROM horoscopes WHERE zodiacSignId = :zodiacSignId ORDER BY createdAt DESC")
    fun observeForSign(zodiacSignId: Long): Flow<List<HoroscopeEntity>>

    @Upsert
    suspend fun upsert(horoscope: HoroscopeEntity): Long

    @Delete
    suspend fun delete(horoscope: HoroscopeEntity)
}