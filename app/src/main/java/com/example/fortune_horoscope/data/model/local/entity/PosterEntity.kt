package com.example.fortune_horoscope.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "posters",
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
data class PosterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val zodiacSignId: Long,
    val title: String,
    val paletteName: String,
    val downloaded: Boolean = false
)