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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.data.model.ZodiacSign
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState

@Composable
fun ZodiacDetailsScreen(uiState: ScreenUiState<ZodiacSign?>) {
    Box(
        modifier = Modifier
            .background(backgroundGradient)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Zodiac Details",
                color = StarGold,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            when (uiState) {
                ScreenUiState.Init, ScreenUiState.Loading -> StateMessage("Loading zodiac details...")
                is ScreenUiState.Error -> StateMessage(uiState.message, Color.Red)
                is ScreenUiState.Success -> {
                    val sign = uiState.data
                    if (sign == null) {
                        StateMessage("No zodiac details saved yet.")
                    } else {
                        ZodiacDetailsContent(sign)
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun ZodiacDetailsContent(sign: ZodiacSign) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = sign.name, color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text(text = sign.dateRange, color = StarGold)
            DetailRow(label = "System", value = sign.system)
            DetailRow(label = "Element", value = sign.element)
            DetailRow(label = "Ruler", value = sign.ruler)
            Text(text = "Description", color = Aqua, fontWeight = FontWeight.Bold)
            Text(text = sign.description, color = Color.White)
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, color = Aqua, fontWeight = FontWeight.Bold)
        Text(text = value, color = Color.White)
    }
}

@Composable
private fun StateMessage(message: String, color: Color = Color.White) {
    Text(text = message, color = color, modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    ZodiacDetailsScreen(
        uiState = ScreenUiState.Success(
            ZodiacSign(1, "Taurus", "Western", "April 20 - May 20", "Venus", "Earth", "Reliable and grounded.")
        )
    )
}
