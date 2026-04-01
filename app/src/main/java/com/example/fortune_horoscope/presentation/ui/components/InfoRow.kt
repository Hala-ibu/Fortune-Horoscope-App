package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.EarthSand
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer

@Composable
fun InfoRow(title: String?, imageVector: ImageVector? = null, additionalInfo: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageVector != null) {
            Icon(imageVector, contentDescription = null, modifier = Modifier.size(24.dp), tint = StarGold)
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (title != null) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge, color = LightPurpleContainer)
        }
        Spacer(modifier = Modifier.weight(1f))
        if (additionalInfo != null) {
            Text(text = additionalInfo, color = EarthSand, style = MaterialTheme.typography.bodyMedium)
        }
    }
}