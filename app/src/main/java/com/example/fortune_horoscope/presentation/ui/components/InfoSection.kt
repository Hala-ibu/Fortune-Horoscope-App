package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Gold
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Lemon
import com.example.fortune_horoscope.presentation.ui.util.InfoRowData

@Composable
fun InfoSection(title: String, rows: List<InfoRowData>, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(),colors = CardDefaults.cardColors(containerColor = Lemon)) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(text = title, style = MaterialTheme.typography.titleLarge,color= Gold)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            rows.forEach { row ->
                InfoRow(
                    title = row.title,
                    imageVector = row.imageVector,
                    additionalInfo = row.additionalInfo
                )
            }
        }
    }
}
