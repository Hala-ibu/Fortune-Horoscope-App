package com.example.fortune_horoscope.presentation.ui.screens.HabbitTracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.data.model.Habit
import com.example.fortune_horoscope.presentation.theme.GlassWhite
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.StarGold
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState

@Composable
fun RitualsScreen(
    uiState: ScreenUiState<List<Habit>>,
    onAddHabit: (String, String) -> Unit,
    onToggleComplete: (Habit) -> Unit,
    onDeleteHabit: (Habit) -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_large))) {
                Text(
                    text = "Daily Rituals",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Room-backed habits with create, read, update, and delete actions",
                    color = Indigo.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Habit title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        onAddHabit(title, category)
                        title = ""
                        category = ""
                    },
                    enabled = title.isNotBlank() && category.isNotBlank()
                ) {
                    Text("Create habit")
                }
            }
        }

        when (uiState) {
            ScreenUiState.Init, ScreenUiState.Loading -> item { StateMessage("Loading rituals...") }
            is ScreenUiState.Error -> item { StateMessage(uiState.message, Color.Red) }
            is ScreenUiState.Success -> {
                if (uiState.data.isEmpty()) {
                    item { StateMessage("No rituals yet. Create your first one above.") }
                } else {
                    items(uiState.data, key = { it.id }) { habit ->
                        HabitCard(
                            habit = habit,
                            onToggleComplete = onToggleComplete,
                            onDeleteHabit = onDeleteHabit
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@Composable
private fun HabitCard(
    habit: Habit,
    onToggleComplete: (Habit) -> Unit,
    onDeleteHabit: (Habit) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Checkbox(
                    checked = habit.completedToday,
                    onCheckedChange = { onToggleComplete(habit) }
                )
                Column {
                    Text(text = habit.title, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${habit.streak} night streak • ${habit.category}",
                        color = StarGold,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            OutlinedButton(onClick = { onDeleteHabit(habit) }) {
                Text("Delete")
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
fun RitualsScreenPreview() {
    RitualsScreen(
        uiState = ScreenUiState.Success(
            listOf(Habit(1, 1, "Moonlight Meditation", "Peace", 12, false))
        ),
        onAddHabit = { _, _ -> },
        onToggleComplete = { },
        onDeleteHabit = { }
    )
}