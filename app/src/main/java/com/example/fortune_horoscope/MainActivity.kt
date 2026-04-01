package com.example.fortune_horoscope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fortune_horoscope.presentation.theme.FortunehoroscopeTheme
import com.example.fortune_horoscope.presentation.ui.screens.ArtPoster.PosterSigns
import com.example.fortune_horoscope.presentation.ui.screens.Home.HomeScreen
import com.example.fortune_horoscope.presentation.ui.screens.Login.LoginScreen
import com.example.fortune_horoscope.presentation.ui.screens.Registration.RegistrationScreen
import com.example.fortune_horoscope.presentation.ui.screens.Relax.RelaxPreview
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.DashboardScreenPreview
import com.example.fortune_horoscope.presentation.ui.screens.ZodiacDetails.ZodiacDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FortunehoroscopeTheme {
                //ZodiacDetail()
                //PosterSigns()
                //HomeScreen()
                //RegistrationScreen()
                //LoginScreen()
            }
        }
    }
}
