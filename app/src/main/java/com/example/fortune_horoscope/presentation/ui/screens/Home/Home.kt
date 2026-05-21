package com.example.fortune_horoscope.presentation.ui.screens.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.presentation.theme.Cinzel
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState

@Composable
fun HomeScreen(
    uiState: ScreenUiState<List<Horoscope>>,
    onExploreClick: () -> Unit
) {
    val featuredHoroscope = (uiState as? ScreenUiState.Success)?.data?.firstOrNull()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Box(
            modifier = Modifier
                .size(500.dp)
                .blur(50.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(StarGold.copy(alpha = 0.15f), Color.Transparent),
                        center = Offset(200f, 200f),
                        radius = 600f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Column {
                Text(
                    text = "WELCOME",
                    fontSize = 30.sp,
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.Medium,
                    color = StarGold,
                    letterSpacing = 8.sp
                )
                Text(
                    text = featuredHoroscope?.mood ?: "Cosmic Friend",
                    fontSize = 64.sp,
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.offset(y = (-12).dp),
                    lineHeight = 68.sp
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(top = 8.dp),
                    thickness = 2.dp,
                    color = StarGold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(bottom = 40.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(GlassWhite)
                    .border(
                        BorderStroke(3.dp, Brush.verticalGradient(listOf(StarGold.copy(0.7f), Color.Transparent))),
                        RoundedCornerShape(32.dp)
                    )
                    .clickable(onClick = onExploreClick)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .weight(1.1f)
                            .padding(24.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        when (uiState) {
                            ScreenUiState.Init, ScreenUiState.Loading -> Text(
                                text = "Loading your daily horoscope...",
                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = Cinzel,
                                color = Color.White
                            )
                            is ScreenUiState.Error -> Text(
                                text = uiState.message,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Red
                            )
                            is ScreenUiState.Success -> Text(
                                text = featuredHoroscope?.message ?: "Tap to explore the zodiac library.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = Cinzel,
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                lineHeight = 32.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = featuredHoroscope?.let { "Lucky #${it.luckyNumber} • ${it.title}" } ?: "Tap to Explore",
                            fontSize = 10.sp,
                            color = LightPurpleContainer,
                            letterSpacing = 2.sp
                        )
                    }
                    Box(modifier = Modifier.weight(0.9f).fillMaxHeight()) {
                        Image(
                            painter = painterResource(id = R.drawable.hourglass),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = ScreenUiState.Success(
            listOf(Horoscope(1, 1, "Daily Reading", "The stars align for those who dare to look up.", "Grounded", 6))
        ),
        onExploreClick = { }
    )
}