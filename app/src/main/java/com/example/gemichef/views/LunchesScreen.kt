package com.example.gemichef.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gemichef.models.Meal
import com.example.gemichef.ui.theme.GemiChefTheme

@Composable
fun LunchesScreen(
    meals: List<Meal>,
    day: String,
    modifier: Modifier = Modifier
){
    Text(day)
}

@Preview
@Composable
fun LunchesScreenPreview() {
    GemiChefTheme {
        LunchesScreen(
            emptyList(),
            "Monday"
        )
    }
}