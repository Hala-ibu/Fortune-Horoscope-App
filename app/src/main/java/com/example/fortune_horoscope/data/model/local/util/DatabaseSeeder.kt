package com.example.fortune_horoscope.data.local.util

import com.example.fortune_horoscope.data.local.dao.HabitDao
import com.example.fortune_horoscope.data.local.dao.HoroscopeDao
import com.example.fortune_horoscope.data.local.dao.UserDao
import com.example.fortune_horoscope.data.local.dao.ZodiacSignDao
import com.example.fortune_horoscope.data.local.entity.HabitEntity
import com.example.fortune_horoscope.data.local.entity.HoroscopeEntity
import com.example.fortune_horoscope.data.local.entity.UserEntity
import com.example.fortune_horoscope.data.local.entity.ZodiacSignEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeeder @Inject constructor(
    private val userDao: UserDao,
    private val zodiacSignDao: ZodiacSignDao,
    private val horoscopeDao: HoroscopeDao,
    private val habitDao: HabitDao
) {
    suspend fun seedIfNeeded() {
        if (zodiacSignDao.count() == 0) {
            val taurusId = zodiacSignDao.upsert(
                ZodiacSignEntity(
                    name = "Taurus",
                    system = "Western",
                    dateRange = "April 20 - May 20",
                    ruler = "Venus",
                    element = "Earth",
                    description = "Reliable, grounded, patient, and deeply connected to comfort and beauty."
                )
            )
            val piscesId = zodiacSignDao.upsert(
                ZodiacSignEntity(
                    name = "Pisces",
                    system = "Western",
                    dateRange = "February 19 - March 20",
                    ruler = "Neptune",
                    element = "Water",
                    description = "Intuitive, compassionate, artistic, and guided by dreams."
                )
            )
            horoscopeDao.upsert(
                HoroscopeEntity(
                    zodiacSignId = taurusId,
                    title = "Today's Taurus Reading",
                    message = "A calm choice brings a lucky opportunity closer today.",
                    mood = "Grounded",
                    luckyNumber = 6
                )
            )
            horoscopeDao.upsert(
                HoroscopeEntity(
                    zodiacSignId = piscesId,
                    title = "Today's Pisces Reading",
                    message = "Trust your intuition before making plans with others.",
                    mood = "Inspired",
                    luckyNumber = 9
                )
            )
        }

        if (habitDao.count() == 0) {
            val demoUser = userDao.getByEmail("halakablaoui@gmail.com") ?: UserEntity(
                fullName = "Hala",
                email = "halakablaoui@gmail.com",
                password = "14052007"
            ).let { userDao.upsert(it); userDao.getByEmail(it.email)!! }
            habitDao.upsertAll(
                listOf(
                    HabitEntity(userId = demoUser.id, title = "Moonlight Meditation", streak = 12, category = "Peace"),
                    HabitEntity(userId = demoUser.id, title = "Tarot Journaling", streak = 5, category = "Insight"),
                    HabitEntity(userId = demoUser.id, title = "Crystal Cleansing", streak = 3, category = "Energy")
                )
            )
        }
    }
}