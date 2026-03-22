package com.example.fortune_horoscope.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.res.dimensionResource
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Lemon
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.ui.components.InfoSection
import com.example.fortune_horoscope.presentation.ui.components.Title
import com.example.fortune_horoscope.presentation.ui.util.InfoRowData

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        ZodiacDetail()
    }
}

@Composable
fun ZodiacDetail() {
    val Facts = listOf("Ruler: Venus", "Color:🩷,💚", "Greatest Compatibility: Scorpio, Cancer","Lucky Numbers: 2, 6, 9, 12, 24",
        "Dates: April 20 - May 20","Strengths: Reliable, patient, devoted, responsible, stable","Weaknesses: Stubborn, possessive, uncompromising",
        "Taurus likes: Gardening, cooking, music, romance, working with hands","Taurus dislikes: Sudden changes, complications")
    val Description = listOf("Practical and well-grounded, Taurus is the sign that harvests the fruits of labor.",
        "They feel the need to always be surrounded by love and beauty, turned to the material world, hedonism, and physical pleasures.",
        " Stable and conservative, this is one of the most reliable signs of the zodiac, ready to endure and stick ")

    Box(modifier=Modifier.background(Aqua).fillMaxSize()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Title(title="Taurus")

        ZodiacCard(Birthday = "May 14", Element = "Element: Earth")

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(title="Facts:",
            rows= Facts.map{InfoRowData(it, imageVector = Icons.Default.Star)})

        Spacer(modifier = Modifier.height(16.dp))

        InfoSection(title="Description:",
            rows= Description.map{InfoRowData(it, imageVector = Icons.Default.FavoriteBorder)})
    }
}}

@Composable
fun ZodiacCard(
    Birthday: String,
    Element:String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth(),colors = CardDefaults.cardColors(containerColor = Indigo))
    {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(0.6f)
                        .aspectRatio(1f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Lemon),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "star shape",
                        modifier = Modifier.fillMaxSize(0.6f),
                        tint=Indigo
                    )
                }
                Column(
                    modifier = Modifier.weight(0.4f).padding(start = 16.dp)
                ) {
                    Text(text = Birthday, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Magenta)
                    Text(text = Element, style = MaterialTheme.typography.bodyLarge,color=Lemon)
                }

            }
        }
    }
}