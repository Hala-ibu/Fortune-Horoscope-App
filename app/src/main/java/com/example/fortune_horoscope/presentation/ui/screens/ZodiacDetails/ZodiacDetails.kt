package com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.FortunehoroscopeTheme
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.components.Title
import com.example.fortune_horoscope.presentation.ui.components.ZodiacTabButton
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.component.ZodiacCard
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.component.infotoshow
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    FortunehoroscopeTheme {
        ZodiacDetailsScreen()
    }
}

@Composable
fun ZodiacDetailsScreen() {
    val Facts = listOf("Ruler: Venus", "Color:🩷,💚", "Greatest Compatibility: Scorpio, Cancer","Lucky Numbers: 2, 6, 9, 12, 24",
        "Dates: April 20 - May 20","Strengths: Reliable, patient, devoted, responsible, stable","Weaknesses: Stubborn, possessive, uncompromising",
        "Taurus likes: Gardening, cooking, music, romance, working with hands","Taurus dislikes: Sudden changes, complications")
    val Description = listOf("Practical and well-grounded, Taurus is the sign that harvests the fruits of labor.",
        "They feel the need to always be surrounded by love and beauty, turned to the material world, hedonism, and physical pleasures.",
        " Stable and conservative, this is one of the most reliable signs of the zodiac, ready to endure and stick ")
    var activeZodiac by remember { mutableStateOf("Facts") }
    val scrollDemoList = List(50) { index -> "Item #${index + 1} — scroll demo" }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier=Modifier.background(backgroundGradient).fillMaxSize()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        Title(title="Taurus")

        ZodiacCard(Birthday = "May 14", Element = "Element: Earth")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ZodiacTabButton(
                text = "Facts",
                isSelected = activeZodiac == "Facts",
                onClick = { activeZodiac = "Facts" },
                modifier = Modifier.weight(1f)
            )

            ZodiacTabButton(
                text = "Description",
                isSelected = activeZodiac == "Description",
                onClick = { activeZodiac = "Description" },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (activeZodiac == "Facts") {
            infotoshow(title="Facts:",
                Facts)
        } else {
            infotoshow(title="Description:",
                Description)
        }
        Spacer(modifier = Modifier.height(100.dp))
        }
        FloatingActionButton(
            containerColor = Indigo,
            contentColor = Aqua,
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)

        ) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Top")
        }
    }
}



