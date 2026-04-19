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
import com.example.fortune_horoscope.presentation.ui.util.AuthValidators

@Composable
fun RegistrationScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val fullNameError = AuthValidators.validateFullName(fullName)
    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)
    val confirmPasswordError = AuthValidators.validateConfirmPassword(
        password = password,
        confirmPassword = confirmPassword
    )

    val isRegisterEnabled = fullNameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null &&
            fullName.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank()

    RegistrationScreenContent(
        fullName = fullName,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        passwordVisible = passwordVisible,
        confirmPasswordVisible = confirmPasswordVisible,
        fullNameError = fullNameError,
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError,
        isRegisterEnabled = isRegisterEnabled,
        onFullNameChange = { fullName = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        onConfirmPasswordVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible },
        onRegisterClick = onNavigateToHome,
        onGoogleClick = { },
        onAppleClick = { },
        onLoginClick = onNavigateToLogin
    )
}

@Composable
private fun RegistrationScreenContent(
    fullName: String,
    email: String,
    password: String,
    confirmPassword: String,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    fullNameError: String?,
    emailError: String?,
    passwordError: String?,
    confirmPasswordError: String?,
    isRegisterEnabled: Boolean,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onConfirmPasswordVisibilityChange: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    onLoginClick: () -> Unit,
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
                title = stringResource(R.string.create_account),
                subtitle = stringResource(R.string.create_acc_subtitle)
            )
            Spacer(modifier = Modifier.height(24.dp))

            AuthTextField(
                value = fullName,
                onValueChange = onFullNameChange,
                label = "Full Name",
                leadingIcon = { Icon(Icons.Default.Person, null) },
                isError = fullNameError != null,
                errorMessage = fullNameError
            )
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                leadingIcon = { Icon(Icons.Default.Email, null) },
                keyboardType = KeyboardType.Email,
                isError = emailError != null,
                errorMessage = emailError
            )
            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = password,
                onValueChange = onPasswordChange,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = onPasswordVisibilityChange,
                label = "Password",
                isError = passwordError != null,
                errorMessage = passwordError
            )
            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                passwordVisible = confirmPasswordVisible,
                onPasswordVisibilityChange = onConfirmPasswordVisibilityChange,
                label = "Confirm Password",
                isError = confirmPasswordError != null,
                errorMessage = confirmPasswordError
            )
            Spacer(modifier = Modifier.height(24.dp))

            AuthPrimaryButton(
                text = "Register",
                onClick = onRegisterClick,
                enabled = isRegisterEnabled
            )
            Spacer(modifier = Modifier.height(24.dp))

            AuthDivider()
            Spacer(modifier = Modifier.height(24.dp))

            SocialSignInButton(
                text = "Sign up with Google",
                onClick = onGoogleClick,
                icon = { GoogleIcon() }
            )
            Spacer(modifier = Modifier.height(12.dp))

            SocialSignInButton(
                text = "Sign up with Apple",
                onClick = onAppleClick,
                icon = { AppleIcon() }
            )
            Spacer(modifier = Modifier.height(24.dp))

            AuthFooterText(
                normalText = "Already have an account? ",
                actionText = "Login",
                onActionClick = onLoginClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val fullNameError = AuthValidators.validateFullName(fullName)
    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)
    val confirmPasswordError = AuthValidators.validateConfirmPassword(
        password = password,
        confirmPassword = confirmPassword
    )

    val isRegisterEnabled = fullNameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null &&
            fullName.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank()

    RegistrationScreenContent(
        fullName = fullName,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        passwordVisible = passwordVisible,
        confirmPasswordVisible = confirmPasswordVisible,
        fullNameError = fullNameError,
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError,
        isRegisterEnabled = isRegisterEnabled,
        onFullNameChange = { fullName = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        onConfirmPasswordVisibilityChange = {
            confirmPasswordVisible = !confirmPasswordVisible
        },
        onRegisterClick = { },
        onGoogleClick = { },
        onLoginClick = { },
        onAppleClick = { })
}
