package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gemichef.R

@Composable
fun TextAndSlider(
    defaultAge: Int,
    onAgeSelected: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var age by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(defaultAge) {
        age = if (defaultAge == 0) "" else defaultAge.toString()
    }

    OutlinedTextField(
        value = age,
        onValueChange = { input ->
            if (input.isEmpty()) {
                age = ""
            } else if (input.toIntOrNull() != null) {
                val newAge = input.toInt().coerceIn(0, 99)
                age = newAge.toString()
            }
            onAgeSelected(age.toIntOrNull() ?: defaultAge)
        },
        placeholder = {
            if (age.isEmpty()) {
                Text(stringResource(R.string.enter_age))
            }
        },
        label = { Text(stringResource(R.string.age)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Slider(
            value = age.toFloatOrNull() ?: 0f,
            onValueChange = { newAge ->
                age = newAge.toInt().toString()
                onAgeSelected(age.toIntOrNull() ?: defaultAge)
            },
            valueRange = 0f..99f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )
    }
}