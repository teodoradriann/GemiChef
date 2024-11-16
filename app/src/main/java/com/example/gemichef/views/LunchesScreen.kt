package com.example.gemichef.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gemichef.models.Meal
import com.example.gemichef.models.days
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.views.ui.elements.Bubble

@Composable
fun LunchesScreen(
    meals: List<Meal>,
    onClickedDay: () -> Unit,
    modifier: Modifier = Modifier
){
    LazyRow(
        modifier = modifier
    ) {
        items(days) {
            Bubble(it,
                onClickedDay,
                modifier = modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun LunchesScreenPreview() {
    GemiChefTheme {
        LunchesScreen(
            emptyList(),
            {}
        )
    }
}