package com.example.gemichef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.viewmodels.PersonViewModel
import com.example.gemichef.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GemiChefTheme {
                val personViewModel: PersonViewModel = viewModel()
                MainScreen(personViewModel = personViewModel)
            }
        }
    }
}