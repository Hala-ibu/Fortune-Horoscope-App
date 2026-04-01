package com.example.fortune_horoscope.presentation.ui.screens.Login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.backgroundGradient
import com.example.fortune_horoscope.presentation.ui.components.AppleIcon
import com.example.fortune_horoscope.presentation.ui.components.AuthDivider
import com.example.fortune_horoscope.presentation.ui.components.AuthFooterText
import com.example.fortune_horoscope.presentation.ui.components.AuthHeader
import com.example.fortune_horoscope.presentation.ui.components.AuthPrimaryButton
import com.example.fortune_horoscope.presentation.ui.components.AuthTextField
import com.example.fortune_horoscope.presentation.ui.components.GoogleIcon
import com.example.fortune_horoscope.presentation.ui.components.PasswordTextField
import com.example.fortune_horoscope.presentation.ui.components.SocialSignInButton
import com.example.fortune_horoscope.presentation.util.AuthValidators

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val isFormValid = emailError == null && passwordError == null && email.isNotEmpty() && password.isNotEmpty()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .verticalScroll(rememberScrollState())
    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = "Welcome Back",
            subtitle = "Login to continue your journey"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        AuthTextField(
            value = email,
            onValueChange = { newValue ->
                email = newValue
                emailError = AuthValidators.validateEmail(newValue)
            },
            label = "Email",
            isError = emailError != null,
            leadingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            errorMessage = emailError
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        PasswordTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = AuthValidators.validatePassword(it)
            },
            label = "Password",
            isError = passwordError != null,
            errorMessage = passwordError        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        AuthPrimaryButton(
            text = "Login",
            enabled = isFormValid,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        AuthDivider()
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        SocialSignInButton(
            text = "Continue with Google",
            onClick = { },
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        SocialSignInButton(
            text = "Continue with Apple",
            onClick = { },
            icon = { AppleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        AuthFooterText(
            normalText = "Don’t have an account? ",
            actionText = "Register",
            onActionClick = { }
        )
    }
}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}