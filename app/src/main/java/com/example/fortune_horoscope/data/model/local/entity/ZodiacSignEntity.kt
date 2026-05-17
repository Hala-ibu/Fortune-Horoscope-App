package com.example.fortune_horoscope.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zodiac_signs")
data class ZodiacSignEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val system: String,
    val dateRange: String,
    val ruler: String,
    val element: String,
    val description: String
)