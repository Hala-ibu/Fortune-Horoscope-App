package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Blue
import com.example.fortune_horoscope.presentation.theme.Gold
import com.example.fortune_horoscope.presentation.theme.Lemon
import com.example.fortune_horoscope.presentation.theme.Magenta

@Composable
fun InfoRow(
    title: String?, modifier: Modifier = Modifier, imageVector: ImageVector? = null, additionalInfo: String? = null
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (imageVector == null) Arrangement.SpaceBetween else Arrangement.Start
    ) {
        if (imageVector != null) {
            Icon(imageVector, contentDescription = null, modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),tint= Gold)
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
        }
        if (title != null) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge,color=Blue)
        }
        if (additionalInfo != null) {
            Text(text = additionalInfo, style = MaterialTheme.typography.bodyLarge, color = Blue)
        }
    }
}