package com.example.fortune_horoscope.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fortune_horoscope.data.local.entity.ZodiacSignEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ZodiacSignDao {
    @Query("SELECT * FROM zodiac_signs ORDER BY name")
    fun observeAll(): Flow<List<ZodiacSignEntity>>

    @Query("SELECT * FROM zodiac_signs WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<ZodiacSignEntity?>

    @Query("SELECT COUNT(*) FROM zodiac_signs")
    suspend fun count(): Int

    @Upsert
    suspend fun upsert(sign: ZodiacSignEntity): Long

    @Upsert
    suspend fun upsertAll(signs: List<ZodiacSignEntity>)

    @Delete
    suspend fun delete(sign: ZodiacSignEntity)
}