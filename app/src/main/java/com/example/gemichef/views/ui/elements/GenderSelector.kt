package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gemichef.R

@Composable
fun GenderSelection(
    gender: String = "",
    onGenderSelected: (String) -> Unit,
    modifier : Modifier = Modifier
) {
    val genders = listOf(stringResource(R.string.male), stringResource(R.string.female))
    var selectedGender by rememberSaveable { mutableStateOf(gender) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.select_gender),
            style = MaterialTheme.typography.bodyLarge)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            genders.forEach { gender ->
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = {
                        selectedGender = gender
                        onGenderSelected(gender)
                    }
                )
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}