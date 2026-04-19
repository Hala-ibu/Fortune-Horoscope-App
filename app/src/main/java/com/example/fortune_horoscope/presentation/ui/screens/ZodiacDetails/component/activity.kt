package com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.EarthSand
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.Glassblack
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.StarGold

@Composable
fun ZodiacCard(
    Birthday: String,
    Element: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                        .height(150.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Glassblack),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.taurusstar),
                        contentDescription = "Zodiac Sign",
                        modifier = Modifier.size(120.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(0.5f).padding(start = 20.dp)
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
                    )
                }
            }
        }
    }
}
@Composable
fun infotoshow(title: String, rows: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        border = BorderStroke(1.dp, GlassWhite)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = StarGold)
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rows.forEach { row ->
                    Text(
                        text = row,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}