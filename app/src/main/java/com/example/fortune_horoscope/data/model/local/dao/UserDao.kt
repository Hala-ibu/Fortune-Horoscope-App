package com.example.fortune_horoscope.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.fortune_horoscope.data.local.entity.UserEntity
import com.example.fortune_horoscope.data.local.entity.UserWithHabits
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY createdAt DESC")
    fun observeUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): UserEntity?

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUserWithHabits(userId: Long): Flow<UserWithHabits?>

    @Upsert
    suspend fun upsert(user: UserEntity): Long

    @Delete
    suspend fun delete(user: UserEntity)
}