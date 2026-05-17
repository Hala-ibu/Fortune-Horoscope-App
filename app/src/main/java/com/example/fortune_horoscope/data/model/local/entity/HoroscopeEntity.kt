package com.example.fortune_horoscope.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "horoscopes",
    foreignKeys = [
        ForeignKey(
            entity = ZodiacSignEntity::class,
            parentColumns = ["id"],
            childColumns = ["zodiacSignId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("zodiacSignId")]
)
data class HoroscopeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val zodiacSignId: Long,
    val title: String,
    val message: String,
    val mood: String,
    val luckyNumber: Int,
    val createdAt: Long = System.currentTimeMillis()
)