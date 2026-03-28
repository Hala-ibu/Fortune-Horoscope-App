package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.DividerGray
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer

@Composable
fun AuthDivider(
    modifier: Modifier = Modifier,
    text: String = "OR"
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(3.dp)
                .background(DividerGray)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small)),
            color = LightPurpleContainer,
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(3.dp)
                .background(DividerGray)
        )
    }
}
