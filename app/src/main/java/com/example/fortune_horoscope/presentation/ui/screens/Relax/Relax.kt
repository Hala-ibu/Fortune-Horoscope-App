package com.example.fortune_horoscope.presentation.ui.screens.Relax

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.ui.components.InfoSection
import com.example.fortune_horoscope.presentation.ui.components.Title
import com.example.fortune_horoscope.presentation.ui.util.InfoRowData

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RelaxPreview() {
    MaterialTheme {
        Relax()
    }
}


@Composable
fun Relax(){
    Box(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(Aqua),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Title(title = "Sleep")

            InfoSection(title = "Before Sleep Routine",listOf(InfoRowData("hi")))

            Spacer(modifier = Modifier.height(8.dp))

            InfoSection(title = "Sleeptime stories",listOf(InfoRowData("hi")))

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
