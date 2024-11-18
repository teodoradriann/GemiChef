package com.example.gemichef.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gemichef.models.Meal
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.views.ui.elements.MealCard

@Composable
fun LunchesScreen(
    meals: List<Meal>,
    day: String,
    modifier: Modifier = Modifier
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(day,
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif
            )
        )
        LazyColumn(
            modifier = modifier
        ) {
            items(meals) {
                if (it.day == day) {
                    MealCard(meal = it)
                }
            }
        }
    }

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