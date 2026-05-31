package com.example.fortune_horoscope.presentation.ui.screens.Fortune


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.data.model.Horoscope
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.Cinzel
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.viewmodel.fortune.FortuneHoroscopeUiState

@Composable
fun FortuneHoroscopeScreen(
    uiState: FortuneHoroscopeUiState,
    onDrawFortune: () -> Unit,
    onRefreshHoroscopes: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { FortuneHeader() }
            item {
                DailyFortuneCard(
                    fortune = uiState.dailyFortune,
                    onDrawFortune = onDrawFortune
                )
            }
            item {
                HoroscopeSectionHeader(
                    isLoading = uiState.isLoading,
                    onRefreshHoroscopes = onRefreshHoroscopes
                )
            }
            uiState.errorMessage?.let { message ->
                item { ErrorBanner(message = message) }
            }
            if (uiState.horoscopes.isEmpty() && uiState.isLoading) {
                item { LoadingCard() }
            } else if (uiState.horoscopes.isEmpty()) {
                item { EmptyHoroscopeCard() }
            } else {
                items(uiState.horoscopes.take(6), key = { it.id }) { horoscope ->
                    HoroscopeReadingCard(horoscope = horoscope)
                }
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

@Composable
private fun FortuneHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "FORTUNE & HOROSCOPE",
            color = StarGold,
            fontFamily = Cinzel,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Text(
            text = "Draw a daily fortune and read cosmic horoscope guidance made for quick entertainment.",
            color = LightPurpleContainer,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DailyFortuneCard(
    fortune: String,
    onDrawFortune: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, StarGold.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape)
                    .background(Brush.radialGradient(listOf(StarGold, Aqua)))
                    .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✦", color = Color.White, fontSize = 42.sp)
            }
            Text(
                text = fortune,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Button(
                onClick = onDrawFortune,
                colors = ButtonDefaults.buttonColors(containerColor = StarGold, contentColor = Aqua)
            ) {
                Text(text = "Draw My Fortune", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun HoroscopeSectionHeader(
    isLoading: Boolean,
    onRefreshHoroscopes: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Daily Horoscopes",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Fresh readings from the network layer",
                color = LightPurpleContainer,
                style = MaterialTheme.typography.bodySmall
            )
        }
        OutlinedButton(
            onClick = onRefreshHoroscopes,
            enabled = !isLoading,
            border = BorderStroke(1.dp, StarGold)
        ) {
            Text(text = if (isLoading) "Loading" else "Refresh", color = StarGold)
        }
    }
}

@Composable
private fun HoroscopeReadingCard(horoscope: Horoscope) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.12f)),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = horoscope.title,
                color = StarGold,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = horoscope.message,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Mood: ${horoscope.mood} • Lucky #${horoscope.luckyNumber}",
                color = LightPurpleContainer,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
private fun ErrorBanner(message: String) {
    Text(
        text = message,
        color = Color(0xFFFFB4AB),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF5D1A1A).copy(alpha = 0.55f))
            .padding(14.dp)
    )
}

@Composable
private fun LoadingCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            CircularProgressIndicator(color = StarGold, modifier = Modifier.size(28.dp))
            Text(text = "Loading today’s readings...", color = Color.White)
        }
    }
}

@Composable
private fun EmptyHoroscopeCard() {
    Text(
        text = "No horoscope readings are available yet. Tap Refresh to try again.",
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(GlassWhite)
            .padding(18.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FortuneHoroscopeScreenPreview() {
    FortuneHoroscopeScreen(
        uiState = FortuneHoroscopeUiState(
            isLoading = false,
            dailyFortune = "A calm choice brings a lucky opportunity closer today.",
            horoscopes = listOf(
                Horoscope(1, 1, "Taurus Reading", "Beauty and patience guide your next step.", "Grounded", 6),
                Horoscope(2, 2, "Pisces Reading", "A dream points toward a real-world answer.", "Inspired", 9)
            )
        ),
        onDrawFortune = {},
        onRefreshHoroscopes = {}
    )
}
