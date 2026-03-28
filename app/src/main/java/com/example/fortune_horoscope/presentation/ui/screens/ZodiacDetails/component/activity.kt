package com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.EarthSand
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.Glassblack
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Lemon
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.StarGold

@Composable
fun ZodiacCard(
    Birthday: String,
    Element:String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent))
    {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Glassblack),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.taurusstar),
                        contentDescription = "Zodiac Sign",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier.weight(0.4f).padding(start = 20.dp)
                ) {
                    Text(
                        text = Birthday,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = LightPurpleContainer
                    )
                    Text(
                        text = Element,
                        style = MaterialTheme.typography.bodyLarge,
                        color = EarthSand
                    )                }

            }
        }
    }
}

