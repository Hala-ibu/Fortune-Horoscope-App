package com.example.fortune_horoscope.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.fortune_horoscope.R
import com.example.fortune_horoscope.presentation.theme.Aqua
import com.example.fortune_horoscope.presentation.theme.DividerGray
import com.example.fortune_horoscope.presentation.theme.Indigo
import com.example.fortune_horoscope.presentation.theme.LightPurpleContainer
import com.example.fortune_horoscope.presentation.theme.Magenta
import com.example.fortune_horoscope.presentation.theme.MysticPurple

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = leadingIcon,
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(dimensionResource(R.dimen.padding_small)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MysticPurple,
                unfocusedBorderColor = DividerGray,
                focusedLabelColor = MysticPurple,
                unfocusedLabelColor = LightPurpleContainer,
                focusedTextColor = LightPurpleContainer,
                unfocusedTextColor = LightPurpleContainer
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
