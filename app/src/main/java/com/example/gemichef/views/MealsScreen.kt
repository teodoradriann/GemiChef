package com.example.gemichef.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gemichef.viewmodels.PersonViewModel
import com.example.gemichef.views.ui.elements.MealCard

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LunchesScreen(
    personViewModel: PersonViewModel,
    day: String,
    modifier: Modifier = Modifier
){
    val uiState by personViewModel.uiState.collectAsState()

    val lunchPlan = if (day != "Favourites") {
        uiState.lunchPlan
    } else {
        uiState.favourites
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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
            modifier = modifier.fillMaxWidth()
        ) {
            items(lunchPlan) { meal ->
                if (meal.day == day) {
                    MealCard(
                        meal = meal,
                        favourites = uiState.favourites,
                        favouritesAdd = { personViewModel.addToFavourites(meal) },
                        favouritesDelete = { personViewModel.removeFromFavourites(meal) }
                    )
                } else if (day == "Favourites") {
                    MealCard(
                        meal = meal,
                        favourites = uiState.favourites,
                        favouritesAdd = { personViewModel.addToFavourites(meal) },
                        favouritesDelete = { personViewModel.removeFromFavourites(meal) }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun LunchesScreenPreview() {

}