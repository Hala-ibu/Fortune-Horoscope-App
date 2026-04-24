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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.fortune_horoscope.presentation.ui.util.AuthValidators

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)

    val isLoginEnabled = emailError == null &&
            passwordError == null &&
            email.isNotBlank() &&
            password.isNotBlank()

    LoginScreenContent(
        email = email,
        password = password,
        passwordVisible = passwordVisible,
        emailError = emailError,
        passwordError = passwordError,
        isLoginEnabled = isLoginEnabled,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onPasswordVisibilityChange = {
            passwordVisible = !passwordVisible
        },
        onLoginClick = onLoginSuccess,
        onRegisterClick = onNavigateToRegister,
        onGoogleClick = { },
        onAppleClick = { }
    )
}

@Composable
private fun LoginScreenContent(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    isLoginEnabled: Boolean,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                title = stringResource(R.string.welcome_back),
                subtitle = stringResource(R.string.login_subtitle)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

            AuthTextField(
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                keyboardType = KeyboardType.Email,
                isError = emailError != null,
                errorMessage = emailError
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            PasswordTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.password),
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = onPasswordVisibilityChange,
                isError = passwordError != null,
                errorMessage = passwordError
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            AuthPrimaryButton(
                text = stringResource(R.string.login),
                onClick = onLoginClick,
                enabled = isLoginEnabled
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

            AuthDivider()
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

            SocialSignInButton(
                text = stringResource(R.string.continue_with_google),
                onClick = onGoogleClick,
                icon = { GoogleIcon() }
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            SocialSignInButton(
                text = "Continue with Apple",
                onClick = onAppleClick,
                icon = { AppleIcon() }
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

            AuthFooterText(
                normalText = stringResource(R.string.don_not_have_account),
                actionText = stringResource(R.string.register),
                onActionClick = onRegisterClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)

    LoginScreenContent(
        email = email,
        password = password,
        passwordVisible = passwordVisible,
        emailError = emailError,
        passwordError = passwordError,
        isLoginEnabled = true,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        onLoginClick = { },
        onRegisterClick = { },
        onGoogleClick = { },
        onAppleClick = { }
    )
}