package com.example.fortune_horoscope.presentation.ui.screens.Explorer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.screens.Explore.components.*

data class ZodiacData(val sign: String, val system: String, val color: Color, val iconRes: Int)
data class ReadingData(val title: String, val imageRes: Int)

@Preview()
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen() {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    //hi is working now git hub?
    var selectedCategory by rememberSaveable { mutableStateOf("All") }

    val allHoroscopes = remember {
        listOf(
            ZodiacData("THULA (BALANCE)", "Vedic", Magenta, R.drawable.quests),
            ZodiacData("PISCES", "Western", MysticPurple, R.drawable.achievement),
            ZodiacData("FUN READING", "fun", Color.Cyan, R.drawable.taurusstar)
        )
    }
    val filteredHoroscopes by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            allHoroscopes.filter { horoscope ->
                val matchesSearch = horoscope.sign.contains(searchQuery, ignoreCase = true)
                matchesSearch
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

        if (filteredHoroscopes.isEmpty()) {
            item {
                EmptySearchState()
            }
        } else {
            stickyHeader {
                FortuneSectionHeader("Today's Horoscopes")
            }
            item { ZodiacCardRow(filteredHoroscopes) }
        }

        item { FortuneTellingBanner() }

        stickyHeader {
            FortuneSectionHeader("Spiritual Guidance")
        }
        item { DailyReadingCard() }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}
