package com.example.fortune_horoscope.presentation.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.data.BottomBarNavigationItem
import com.example.fortune_horoscope.presentation.navigation.BottomBarNavigationItems.items
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.FortunehoroscopeTheme
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.Magenta

object BottomBarNavigationItems {
    val items = listOf(
        BottomBarNavigationItem(
            titleId = R.string.Home,
            iconRes = R.drawable.google_icon,
            route = Screen.Home.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.Explore,
            iconRes = R.drawable.google_icon,
            route = Screen.Explore.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.HabitTracker,
            iconRes = R.drawable.google_icon,
            route = Screen.HabitTracker.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.ArtPoster,
            iconRes = R.drawable.apple__streamline_unicons,
            route = Screen.ArtPoster.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.ZodiacDetails,
            iconRes = R.drawable.google_icon,
            route = Screen.ZodiacDetails.route
        )
    )
}
@Composable
fun BottomBarNavigationComponent(
    items: List<BottomBarNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    Surface(
        shape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.padding_small),
            topEnd = dimensionResource(id = R.dimen.padding_small)
        ),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.padding_small),
            color = Aqua
        )
    ) {
        NavigationBar(
            containerColor = Magenta
        ) {
            items.forEachIndexed { index, item ->
                val selected = selectedItemIndex == index

                NavigationBarItem(
                    modifier = Modifier.wrapContentSize(),
                    selected = selected,
                    onClick = { onItemSelected(index) },
                    label = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = item.titleId),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (selected) Blue else Indigo,
                                    fontSize = 12.sp,
                                    fontWeight =  if (selected) FontWeight.W400 else FontWeight.W300
                                )
                            )
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = stringResource(id = item.titleId),
                            tint = if (selected) Blue else Indigo
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Indigo,
                        unselectedIconColor = Indigo,
                        selectedTextColor = if (selected) Indigo else Blue,
                        unselectedTextColor = Indigo,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarNavigationComponentPreview() {
    FortunehoroscopeTheme {

        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

        BottomBarNavigationComponent(
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = { index -> selectedItemIndex = index }
        )
    }
}
