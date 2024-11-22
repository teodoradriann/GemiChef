package com.example.gemichef.views.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gemichef.R

@Composable
fun TextField(
    @StringRes text: Int,
    fitnessObjective: String,
    onFitnessObjectiveSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var objective by rememberSaveable { mutableStateOf(fitnessObjective) }
    OutlinedTextField(
        value = objective,
        onValueChange = { input ->
            objective = input
            onFitnessObjectiveSelected(objective)
        },
        label = { Text(stringResource(text)) },
        modifier = modifier.padding(top = 16.dp)
            .widthIn(min = 279.dp, max = 279.dp)
            .wrapContentHeight(),
        maxLines = 4
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    TextField(
        text = R.string.fitness_objective,
        fitnessObjective = "",
        onFitnessObjectiveSelected = {}
    )
}