package com.example.fortune_horoscope.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithHabits(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val habits: List<HabitEntity>
)