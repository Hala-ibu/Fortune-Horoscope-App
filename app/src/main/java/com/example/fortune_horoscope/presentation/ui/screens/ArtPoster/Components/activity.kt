package com.example.fortune_horoscope.presentation.ui.screens.ArtPoster.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.presentation.theme.EarthSand
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.TaurusGreen


@Composable
fun Zodiaclists(onSignClick: (Color) -> Unit) {
    val zodiacSigns = listOf("♈", "♉", "♊", "♋", "♌", "♍", "♎", "♏", "♐", "♑", "♒", "♓")

    val colorMap = mapOf(
        "♈" to MysticPurple,
        "♉" to TaurusGreen,
        "♊" to EarthSand,
        "♋" to MysticPurple,
        "♌" to TaurusGreen,
        "♍" to EarthSand,
        "♎" to MysticPurple,
        "♏" to EarthSand,
        "♐" to TaurusGreen,
        "♍" to MysticPurple,
        "♑" to TaurusGreen,
        "♒" to EarthSand,
        "♓" to MysticPurple
    )

    LazyRow(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(zodiacSigns) { icon ->
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(GlassWhite)
                    .clickable {
                        onSignClick(colorMap[icon] ?: GlassWhite)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 28.sp)
            }
        }
    }
}

@Composable
fun DownloadingPosters() {
}