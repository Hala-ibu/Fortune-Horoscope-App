package com.example.fortune_horoscope.presentation.ui.screens.Explore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.*
import com.example.fortune_horoscope.presentation.ui.screens.Explorer.ReadingData
import com.example.fortune_horoscope.presentation.ui.screens.Explorer.ZodiacData

@Composable
fun FortuneTopBar(query: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        placeholder = { Text("Search...", color = Indigo.copy(alpha = 0.6f), fontSize = 14.sp) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Indigo) },
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = GlassWhite,
            focusedContainerColor = GlassWhite,
            unfocusedBorderColor = Magenta.copy(alpha = 0.3f),
            focusedBorderColor = Magenta,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}

@Composable
fun SystemCategoryRow(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("All", "fun", "Western", "Chinese", "Tarot", "space", "Vedic")
    LazyRow(
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(categories) { system ->
            val isSelected = selectedCategory == system

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = if (isSelected) Magenta else GlassWhite,
                border = BorderStroke(1.dp, Magenta.copy(alpha = 0.5f)),
                modifier = Modifier
                    .height(40.dp)
                    .clickable { onCategorySelected(system) }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = system,
                        color = if (isSelected) Color.White else Indigo,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
@Composable
fun ZodiacCardRow(horoscopes: List<ZodiacData>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(horoscopes) { data ->
            ZodiacCard(
                sign = data.sign,
                system = data.system,
                color = data.color,
                iconRes = data.iconRes
            )
        }
    }
}

@Composable
fun DailyReadingList(readings: List<ReadingData>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        readings.forEach { reading ->
            DailyReadingCard(reading.title, reading.imageRes)
        }
    }
}

@Composable
fun DailyReadingCard(title: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .height(220.dp),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Glassblack)))
            )
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(dimensionResource(R.dimen.padding_medium)),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
@Composable
fun ZodiacCard(sign: String, system: String, color: Color, iconRes: Int) {
    Card(
        modifier = Modifier
            .width(210.dp)
            .height(260.dp),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_large)),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                alpha = 0.15f,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(180.dp)
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.radialGradient(
                                0.0f to Color.White,
                                0.5f to Color.White,
                                1.0f to Color.Transparent,
                                center = center,
                                radius = size.width / 1.8f
                            ),
                            blendMode = BlendMode.DstIn
                        ) },
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(sign, color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(system, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
            }
        }
    }
}

@Composable
fun FortuneTellingBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
        colors = CardDefaults.cardColors(containerColor = Glassblack),
        border = BorderStroke(1.dp, Magenta.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.avatar_size))
                    .background(
                        brush = Brush.sweepGradient(listOf(Magenta, MysticPurple, Aqua)),
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
            Column(modifier = Modifier.weight(1f)) {
                Text("DAILY INSIGHTS", fontWeight = FontWeight.Black, fontSize = 11.sp, color = StarGold)
                Text("Unlock your personalized path with premium readings.", fontSize = 13.sp, color = Indigo)
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Indigo)
        }
    }
}

@Composable
fun DailyReadingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .height(220.dp),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.habits),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Glassblack)))
            )
            Text(
                "Angel Numbers & Their Meanings",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(dimensionResource(R.dimen.padding_medium)),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun FortuneSectionHeader(text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_medium),
                top = 16.dp,
                bottom = 8.dp
            ),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Indigo
        )
    }
}

@Composable
fun EmptySearchState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_large)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Indigo.copy(alpha = 0.3f),
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Destiny is hidden",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "The stars haven't aligned for this search. Try a different sign, category, or keyword.",
            color = Indigo.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}