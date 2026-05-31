package com.example.fortune_horoscope.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HoroscopeRequestDto(
    @SerializedName("userId") val zodiacSignId: Long,
    val title: String,
    @SerializedName("body") val message: String
)
