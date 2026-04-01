package com.example.fortune_horoscope.presentation.ui.screens.ArtPoster

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.presentation.theme.EarthSand
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.TaurusGreen
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.components.ZodiacTabButton
import com.example.fortune_horoscope.presentation.ui.screens.ArtPoster.Components.Zodiaclists

@Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PosterSigns() {
    val context = LocalContext.current
    var selectedCardColor by remember { mutableStateOf(GlassWhite) }

    Box(modifier = Modifier.background(backgroundGradient)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = selectedCardColor)
            ) {
                Column {
                    Zodiaclists(onSignClick = { newColor -> selectedCardColor = newColor })
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Your Poster Content",
                            color = LightPurpleContainer,
                            fontSize = 24.sp
                        )
                    }
                }
            }
            ZodiacTabButton(
                text = "Download",
                isSelected = true,
                onClick = {
                    Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .width(140.dp)
            )

        }
    }
}
