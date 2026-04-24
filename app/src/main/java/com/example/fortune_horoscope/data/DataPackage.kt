package com.example.fortune_horoscope.data

import androidx.annotation.StringRes

data class BottomBarNavigationItem(
    @StringRes val titleId: Int,
    val iconRes: Int,
    val route: String,
    val unreadCount: Int? = 0
)

