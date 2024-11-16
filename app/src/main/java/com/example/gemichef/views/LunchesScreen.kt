package com.example.gemichef.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gemichef.ui.theme.GemiChefTheme

@Composable
fun LunchesScreen(
    modifier: Modifier = Modifier
){
    Text("Lunch Planner",
        modifier = modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun LunchesScreenPreview() {
    GemiChefTheme {
        LunchesScreen()
    }
}