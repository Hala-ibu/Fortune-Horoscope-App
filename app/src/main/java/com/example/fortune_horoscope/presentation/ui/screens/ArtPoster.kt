package com.example.fortune_horoscope.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PosterSigns(){
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp,end=16.dp, bottom = 16.dp, top = 40.dp)
    ){
        Card(){
            Zodiaclists()
        }
    }
}

@Composable
fun DownloadingPosters(){
}

@Composable
fun Zodiaclists(){
    val zodiacSigns = listOf("♈", "♉", "♊", "♋", "♌", "♍", "♎", "♏", "♐", "♑", "♒", "♓")

    LazyRow(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(zodiacSigns) { icon ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 30.sp)
            }
        }
    }
}
