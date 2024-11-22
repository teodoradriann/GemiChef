package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gemichef.R
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.views.Screens

@Composable
fun BottomBar(
    navController: NavHostController,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp))
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(R.string.home),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text(
                stringResource(R.string.home),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier.offset(y = (-8).dp))},
            selected = false,
            onClick = {
                onClick()
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                if (currentRoute != Screens.MainScreen.name) {
                    navController.navigate(Screens.MainScreen.name)
                }
            },
            modifier = modifier.offset(y = 8.dp)
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    GemiChefTheme {

    }
}