package com.example.fortune_horoscope.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HoroscopeDto(
    val id: Long? = null,
    @SerializedName("userId") val zodiacSignId: Long = 1,
    val title: String,
    @SerializedName("body") val message: String
)
