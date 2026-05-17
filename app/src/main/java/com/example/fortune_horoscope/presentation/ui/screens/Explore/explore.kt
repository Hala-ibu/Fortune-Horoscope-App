package com.example.fortune_horoscope.presentation.ui.screens.Explorer


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.data.model.ZodiacSign
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.TaurusGreen
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.DailyReadingCard
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.EmptySearchState
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.FortuneSectionHeader
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.FortuneTellingBanner
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.FortuneTopBar
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.SystemCategoryRow
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.ZodiacCardRow
import com.example.fortune_horoscope.presentation.viewmodel.ScreenUiState

data class ZodiacData(val sign: String, val system: String, val color: Color, val iconRes: Int)
data class ReadingData(val title: String, val imageRes: Int)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(uiState: ScreenUiState<List<ZodiacSign>>) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("All") }
    val allHoroscopes = remember(uiState) {
        (uiState as? ScreenUiState.Success)
            ?.data
            .orEmpty()
            .map { sign -> sign.toZodiacData() }
    }
    val filteredHoroscopes by remember(searchQuery, selectedCategory, allHoroscopes) {
        derivedStateOf {
            allHoroscopes.filter { horoscope ->
                val matchesSearch = horoscope.sign.contains(searchQuery, ignoreCase = true) ||
                        horoscope.system.contains(searchQuery, ignoreCase = true)
                val matchesCategory = selectedCategory == "All" ||
                        horoscope.system.equals(selectedCategory, ignoreCase = true)
                matchesSearch && matchesCategory
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        item { FortuneTopBar(searchQuery) { searchQuery = it } }
        item { SystemCategoryRow(selectedCategory) { selectedCategory = it } }

        when (uiState) {
            ScreenUiState.Init,
            ScreenUiState.Loading -> item { ExploreStateMessage("Loading zodiac signs from Room...") }

            is ScreenUiState.Error -> item { ExploreStateMessage(uiState.message, Color.Red) }

            is ScreenUiState.Success -> {
                if (filteredHoroscopes.isEmpty()) {
                    item { EmptySearchState() }
                } else {
                    stickyHeader {
                        FortuneSectionHeader("Today's Horoscopes")
                    }
                    item { ZodiacCardRow(filteredHoroscopes) }
                }
            }
        }

        item { FortuneTellingBanner() }

        stickyHeader {
            FortuneSectionHeader("Spiritual Guidance")
        }
        item { DailyReadingCard() }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
private fun ExploreStateMessage(message: String, color: Color = Color.White) {
    Text(text = message, color = color)
}

private fun ZodiacSign.toZodiacData(): ZodiacData {
    val cardColor = when (element.lowercase()) {
        "earth" -> TaurusGreen
        "water" -> MysticPurple
        "fire" -> Magenta
        else -> Color.Cyan
    }
    val icon = when (name.lowercase()) {
        "taurus" -> R.drawable.taurusstar
        "pisces" -> R.drawable.achievement
        else -> R.drawable.quests
    }
    return ZodiacData(
        sign = name.uppercase(),
        system = system,
        color = cardColor,
        iconRes = icon
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(
        uiState = ScreenUiState.Success(
            listOf(
                ZodiacSign(1, "Taurus", "Western", "April 20 - May 20", "Venus", "Earth", "Reliable and grounded."),
                ZodiacSign(2, "Pisces", "Western", "February 19 - March 20", "Neptune", "Water", "Intuitive and compassionate.")
            )
        )
    )
}
