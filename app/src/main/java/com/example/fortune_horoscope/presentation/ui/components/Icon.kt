package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Indigo

@Composable
fun GoogleIcon() {
    Icon(
        painter = painterResource(R.drawable.google_icon),
        contentDescription = "Google",
        tint = Indigo
    )
}

@Composable
fun AppleIcon() {
    Icon(
        painter = painterResource(R.drawable.apple__streamline_unicons),
        contentDescription = "Apple",
        tint = Indigo
    )
}