package com.example.fortune_horoscope.data.model

data class User(val id: Long, val fullName: String, val email: String, val zodiacSignId: Long?)
data class ZodiacSign(val id: Long, val name: String, val system: String, val dateRange: String, val ruler: String, val element: String, val description: String)
data class Horoscope(val id: Long, val zodiacSignId: Long, val title: String, val message: String, val mood: String, val luckyNumber: Int)
data class Habit(val id: Long, val userId: Long, val title: String, val category: String, val streak: Int, val completedToday: Boolean)
data class JournalEntry(val id: Long, val userId: Long, val title: String, val body: String, val createdAt: Long)
data class Poster(val id: Long, val zodiacSignId: Long, val title: String, val paletteName: String, val downloaded: Boolean)