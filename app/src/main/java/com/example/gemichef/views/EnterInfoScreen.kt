package com.example.gemichef.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gemichef.R
import com.example.gemichef.models.heights
import com.example.gemichef.models.weights
import com.example.gemichef.views.ui.elements.DropdownMenu
import com.example.gemichef.views.ui.elements.GenderSelection
import com.example.gemichef.views.ui.elements.TextAndSlider
import com.example.gemichef.views.ui.elements.TextField

@Composable
fun EnterInfo(
    age: Int,
    weight: Int,
    height: Int,
    fitnessObjective: String,
    gender: String = "",
    onGenderSelected: (String) -> Unit,
    onAgeSelected: (Int) -> Unit,
    onFitnessObjectiveSelected: (String) -> Unit,
    onHeightSelected: (Int) -> Unit,
    onWeightSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            border = CardDefaults.outlinedCardBorder(),
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                TextField(R.string.fitness_objective, fitnessObjective, onFitnessObjectiveSelected, modifier)
                TextAndSlider(age, onAgeSelected, modifier)
                DropdownMenu(height, R.string.height, "cm", heights,
                    onHeightSelected, modifier)
                DropdownMenu(weight, R.string.weight, "kg", weights,
                    onWeightSelected, modifier)
                GenderSelection(
                    gender = gender,
                    onGenderSelected = { onGenderSelected(it) },
                    modifier
                )
            }
        }
    }
}


@Preview
@Composable
fun EnterInfoPreview() {
    EnterInfo(
        weight = 0,
        height = 0,
        age = 0,
        fitnessObjective = "",
        gender = "",
        onGenderSelected = {},
        onAgeSelected = {},
        onFitnessObjectiveSelected = {},
        onHeightSelected = {},
        onWeightSelected = {},
        modifier = Modifier
    )
}