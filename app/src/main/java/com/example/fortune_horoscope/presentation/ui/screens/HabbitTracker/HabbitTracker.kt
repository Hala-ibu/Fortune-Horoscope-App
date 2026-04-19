package com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker.component.RitualItem
import com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker.util.RitualModel

@Composable
fun RitualsScreen(
) {
    var rituals = listOf(
        RitualModel("Moonlight Meditation", 12, "Peace"),
        RitualModel("Tarot Journaling", 5, "Insight"),
        RitualModel("Crystal Cleansing", 3, "Energy"),
        RitualModel("Morning Sun Greeting", 8, "Vitality"),
        RitualModel("Aura Protection", 14, "Peace"),
        RitualModel("Zodiac Affirmation", 20, "Mindset")
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            item {
                Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_large))) {
                    Text(
                        text = "Daily Rituals",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Align your energy with the cosmos",
                        color = Indigo.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            items(rituals) { ritual ->
                RitualItem(
                    title = ritual.title,
                    streak = "${ritual.streak} Night Streak",
                    category = ritual.category
                )
            }

            item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RitualsScreenPreview() {
    RitualsScreen(
    )
}