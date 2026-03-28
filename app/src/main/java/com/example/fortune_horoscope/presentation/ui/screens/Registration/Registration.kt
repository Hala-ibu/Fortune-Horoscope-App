package com.example.fortune_horoscope.presentation.ui.screens.Registration

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun RegistrationScreen(modifier: Modifier = Modifier) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .verticalScroll(rememberScrollState())
    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = "Create Account",
            subtitle = "Register and start building your journey"
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                fullNameError = AuthValidators.validateFullName(it)
            },
            label = "Full Name",
            leadingIcon = { androidx.compose.material3.Icon(Icons.Default.Person, null) },
            isError = fullNameError != null,
            errorMessage = fullNameError        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        AuthTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = AuthValidators.validateEmail(it)
            },
            label = "Email",
            leadingIcon = { androidx.compose.material3.Icon(Icons.Default.Email, null) },
            isError = emailError != null,
            errorMessage = emailError        )
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
        PasswordTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = AuthValidators.validateConfirmPassword(password, it)
            },
            label = "Confirm Password",
            isError = confirmPasswordError != null,
            errorMessage = confirmPasswordError        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        AuthPrimaryButton(
            text = "Register",
            onClick = {
                fullNameError = AuthValidators.validateFullName(fullName)
                emailError = AuthValidators.validateEmail(email)
                passwordError = AuthValidators.validatePassword(password)
                confirmPasswordError = AuthValidators.validateConfirmPassword(password, confirmPassword)

                if (fullNameError == null && emailError == null &&
                    passwordError == null && confirmPasswordError == null) { }
            })
        Spacer(modifier = Modifier.height(24.dp))
        AuthDivider()
        Spacer(modifier = Modifier.height(24.dp))
        SocialSignInButton(
            text = "Sign up with Google",
            onClick = { },
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(12.dp))
        SocialSignInButton(
            text = "Sign up with Apple",
            onClick = { },
            icon = { AppleIcon() }
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthFooterText(
            normalText = "Already have an account? ",
            actionText = "Login",
            onActionClick = { }
        )
    }
}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}