package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.DividerGray
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.MysticPurple
import com.example.fortune_horoscope.presentation.theme.StarGold

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MysticPurple
                )
            },
            trailingIcon = {
                Text(
                    text = "Show",
                    color = StarGold,
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_large))
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MysticPurple,
                unfocusedBorderColor = DividerGray,
                focusedLabelColor = MysticPurple,
                unfocusedLabelColor = LightPurpleContainer,
                focusedTextColor = LightPurpleContainer,
                unfocusedTextColor = LightPurpleContainer,
                cursorColor = StarGold
            )
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}