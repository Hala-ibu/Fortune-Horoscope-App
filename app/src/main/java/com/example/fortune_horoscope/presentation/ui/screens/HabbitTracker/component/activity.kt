package com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.StarGold

@Composable
fun RitualItem(
    title: String,
    streak: String,
    category: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Magenta.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when(category) {
                        "Peace" -> Icons.Default.Favorite
                        "Insight" -> Icons.Default.Star
                        else -> Icons.Default.CheckCircle
                    },
                    contentDescription = null,
                    tint = Magenta,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = StarGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = streak,
                        color = Indigo,
                        fontSize = 14.sp
                    )
                }
            }

            IconButton(
                onClick = { /* TODO: Mark ritual complete */ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f))
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Complete",
                    tint = Color.White
                )
            }
        }
    }
}