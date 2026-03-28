package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.TaurusGreen
import com.example.fortune_horoscope.presentation.ui.util.InfoRowData

@Composable
fun InfoSection(title: String, rows: List<InfoRowData>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        border = BorderStroke(1.dp, GlassWhite)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = StarGold)
            Spacer(modifier = Modifier.height(8.dp))
            rows.forEach { row ->
                InfoRow(title = row.title, imageVector = row.imageVector, additionalInfo = row.additionalInfo)
            }
        }
    }
}