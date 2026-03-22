package com.example.fortune_horoscope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fortune_horoscope.presentation.theme.FortunehoroscopeTheme
import com.example.fortune_horoscope.presentation.ui.screens.DashboardScreenPreview
import com.example.fortune_horoscope.presentation.ui.screens.RelaxPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FortunehoroscopeTheme {
                //DashboardScreenPreview()
                RelaxPreview()
            }
        }
    }
}
