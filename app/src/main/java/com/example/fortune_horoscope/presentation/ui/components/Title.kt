package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.Gold
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Magenta

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        color = Magenta
    )
}