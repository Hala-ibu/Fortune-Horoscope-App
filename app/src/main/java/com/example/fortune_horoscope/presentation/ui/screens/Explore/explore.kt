package com.example.fortune_horoscope.presentation.ui.screens.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.presentation.theme.*

data class ExplorerItem(
    val id: Int,
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExplorerScreen() {
    val explorerItems = listOf(
        ExplorerItem(1, "Daily Reading", "Your stars for today.", Icons.Default.AutoAwesome),
        ExplorerItem(2, "Zodiac Profile", "Personality insights.", Icons.Default.AccountCircle),
        ExplorerItem(3, "Moon Phase", "Lunar cycle energy.", Icons.Default.NightsStay),
        ExplorerItem(4, "Compatibility", "Check your matches.", Icons.Default.Favorite),
        ExplorerItem(5, "Crystal Guide", "Energy and healing.", Icons.Default.Diamond),
        ExplorerItem(6, "Tarot Deck", "Reveal the hidden.", Icons.Default.Style)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        // Using LazyVerticalStaggeredGrid from Lab 6
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header inside item { } block as per Lab 6 Part E
            item {
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = "EXPLORER",
                        fontFamily = Cinzel,
                        fontSize = 32.sp,
                        color = StarGold,
                        letterSpacing = 4.sp
                    )
                    Text(
                        text = "Discover the unseen",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }
            }

            // Grid items as per Lab 6 Part F
            items(explorerItems) { item ->
                ExplorerIconCard(item)
            }
        }
    }
}

@Composable
fun ExplorerIconCard(item: ExplorerItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Icon replaces Image from your old design
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = StarGold,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.title,
                color = Color.White,
                fontFamily = Cinzel,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = item.description,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}