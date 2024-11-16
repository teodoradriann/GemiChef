package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gemichef.R
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.views.Screens

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(52.dp)
                )
            },
            label = { Text(
                stringResource(R.string.home),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary)},
            selected = false,
            onClick = {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                if (currentRoute != Screens.MainScreen.name) {
                    navController.navigate(Screens.MainScreen.name)
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "lunches",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(52.dp)
                )
            },
            label = {
                Text(stringResource(R.string.lunches),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary)
            },
            selected = false,
            onClick = {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                if (currentRoute != Screens.LunchPlannerScreen.name) {
                    navController.navigate(Screens.LunchPlannerScreen.name)
                }
            }
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    GemiChefTheme {
        BottomBar(rememberNavController())
    }
}