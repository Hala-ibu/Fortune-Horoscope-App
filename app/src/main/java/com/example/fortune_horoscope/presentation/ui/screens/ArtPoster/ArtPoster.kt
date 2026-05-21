package com.example.fortune_horoscope.presentation.ui.screens.ArtPoster

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.example.fortune_horoscope.data.model.Poster
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState

@Composable
fun PosterSigns(
    uiState: ScreenUiState<List<Poster>>,
    onAddPoster: (String, String) -> Unit,
    onToggleDownloaded: (Poster) -> Unit,
    onDeletePoster: (Poster) -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var palette by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Art Poster Gallery",
                color = StarGold,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Room-backed posters with create, update, and delete actions",
                color = LightPurpleContainer
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Poster title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = palette,
                onValueChange = { palette = it },
                label = { Text("Palette") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    onAddPoster(title, palette)
                    title = ""
                    palette = ""
                },
                enabled = title.isNotBlank() && palette.isNotBlank()
            ) { Text("Create poster") }
        }

        when (uiState) {
            ScreenUiState.Init, ScreenUiState.Loading -> item { StateMessage("Loading posters...") }
            is ScreenUiState.Error -> item { StateMessage(uiState.message, Color.Red) }
            is ScreenUiState.Success -> {
                if (uiState.data.isEmpty()) {
                    item { StateMessage("No posters yet. Create one above.") }
                } else {
                    items(uiState.data, key = { it.id }) { poster ->
                        PosterCard(
                            poster = poster,
                            onToggleDownloaded = onToggleDownloaded,
                            onDeletePoster = onDeletePoster
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(96.dp)) }
    }
}

@Composable
private fun PosterCard(
    poster: Poster,
    onToggleDownloaded: (Poster) -> Unit,
    onDeletePoster: (Poster) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = GlassWhite)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = poster.title, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text(text = "Palette: ${poster.paletteName}", color = StarGold)
            Text(
                text = if (poster.downloaded) "Downloaded" else "Not downloaded yet",
                color = LightPurpleContainer
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { onToggleDownloaded(poster) }) {
                    Text(if (poster.downloaded) "Mark pending" else "Download")
                }
                OutlinedButton(onClick = { onDeletePoster(poster) }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
private fun StateMessage(message: String, color: Color = Color.White) {
    Text(text = message, color = color, modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PosterSignsPreview() {
    PosterSigns(
        uiState = ScreenUiState.Success(listOf(Poster(1, 1, "Taurus Earth Glow", "Earth + Gold", false))),
        onAddPoster = { _, _ -> },
        onToggleDownloaded = { },
        onDeletePoster = { }
    )
}