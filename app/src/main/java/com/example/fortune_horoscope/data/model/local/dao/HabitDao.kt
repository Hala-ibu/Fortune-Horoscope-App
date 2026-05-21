package com.example.fortune_horoscope.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fortune_horoscope.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE userId = :userId ORDER BY title")
    fun observeForUser(userId: Long): Flow<List<HabitEntity>>

    @Query("SELECT COUNT(*) FROM habits")
    suspend fun count(): Int

    @Upsert
    suspend fun upsert(habit: HabitEntity): Long

    @Upsert
    suspend fun upsertAll(habits: List<HabitEntity>)

    @Delete
    suspend fun delete(habit: HabitEntity)
}