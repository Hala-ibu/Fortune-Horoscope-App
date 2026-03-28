package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Cinzel
import com.example.fortune_horoscope.presentation.theme.Magenta

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.headlineLarge,
        fontFamily = Cinzel,
        fontWeight = FontWeight.Bold,
        letterSpacing = 4.sp,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        color = Magenta
    )
}