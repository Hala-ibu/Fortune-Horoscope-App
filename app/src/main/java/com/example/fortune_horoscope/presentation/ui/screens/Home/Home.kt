package com.example.fortune_horoscope.presentation.ui.screens.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Cinzel
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.backgroundGradient

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
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
                        colors = listOf(
                            StarGold.copy(alpha = 0.15f),
                            Color.Transparent
                        ),
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
                    text = "Hala",
                    fontSize = 82.sp,
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.offset(y = (-20).dp),
                    lineHeight = 80.sp
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
                        BorderStroke(3.dp, Brush.verticalGradient(listOf(StarGold.copy(0.7f),
                            Color.Transparent))),
                        RoundedCornerShape(32.dp)
                    )
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .weight(1.1f)
                            .padding(24.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "The stars align for those who dare to look up.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = Cinzel,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            lineHeight = 32.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "EST. 2024",
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color.Transparent, Color.Transparent),
                                    )
                                )
                        )
                    }
                }
            }
        }
    }
}