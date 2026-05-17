package com.example.fortune_horoscope.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fortune_horoscope.data.local.entity.PosterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PosterDao {
    @Query("SELECT * FROM posters ORDER BY title")
    fun observeAll(): Flow<List<PosterEntity>>

    @Query("SELECT * FROM posters WHERE zodiacSignId = :zodiacSignId")
    fun observeForSign(zodiacSignId: Long): Flow<List<PosterEntity>>

    @Upsert
    suspend fun upsert(poster: PosterEntity): Long

    @Delete
    suspend fun delete(poster: PosterEntity)
}