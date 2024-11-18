package com.example.gemichef.views

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gemichef.R
import com.example.gemichef.models.days
import com.example.gemichef.ui.theme.GemiChefTheme
import com.example.gemichef.viewmodels.PersonViewModel
import com.example.gemichef.views.ui.elements.BottomBar
import com.example.gemichef.views.ui.elements.GeminiButton
import com.example.gemichef.views.ui.elements.TopBar
import kotlinx.coroutines.launch

enum class Screens(@StringRes val title: Int){
    MainScreen(title = R.string.main_screen),
    LunchPlannerScreen(title = R.string.lunch_planner)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    personViewModel: PersonViewModel,
    navController: NavHostController = rememberNavController(),
    modifier : Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navStack = navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        navStack.value?.destination?.route ?: Screens.MainScreen.name
    )
    val uiState by personViewModel.uiState.collectAsState()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Planned Meals", modifier = Modifier.padding(16.dp),
                    fontSize = 32.sp)
                HorizontalDivider()
                days.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item,
                            fontSize = 24.sp) },
                        selected = false,
                        onClick = {
                            if (uiState.lunchPlan.isNotEmpty()) {
                                personViewModel.updateSelectedDay(item)
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(Screens.LunchPlannerScreen.name)
                            }
                        },
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = { TopBar(
                currentScreen,
                onMenuClicked = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            )},
            bottomBar = { BottomBar(navController, modifier) }
        ) { padding ->


            NavHost(
                navController = navController,
                startDestination = Screens.MainScreen.name,
                modifier = modifier.padding(padding).padding(top = 28.dp)
            ) {
                composable(
                    route = Screens.MainScreen.name,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    }
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ){
                        Spacer(modifier.padding(16.dp))
                        EnterInfo(
                            age = uiState.age ?: 0,
                            weight = uiState.weight ?: 0,
                            height = uiState.height ?: 0,
                            gender = uiState.gender ?: "",
                            fitnessObjective = uiState.fitnessObjective ?: "",
                            onGenderSelected = {
                                personViewModel.updateGender(it)
                            },
                            onAgeSelected = {
                                personViewModel.updateAge(it)
                            },
                            onFitnessObjectiveSelected = {
                                personViewModel.updateFitnessObjective(it)
                            },
                            onHeightSelected = {
                                personViewModel.updateHeight(it)
                            },
                            onWeightSelected = {
                                personViewModel.updateWeight(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        GeminiButton(
                            onClick = {
                                if (personViewModel.sendToGemini()) {
                                    // TODO: add popup telling user to go to lunch planner
                                } else {
                                    // TODO: add popup telling user to fill in all fields
                                }
                            },
                            modifier = modifier
                        )
                    }
                }
                composable(
                    route = Screens.LunchPlannerScreen.name,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    }
                ) {
                    LunchesScreen(
                        uiState.lunchPlan ?: emptyList(),
                        uiState.selectedDay ?: "Monday"
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun MainScreenPreview() {
    GemiChefTheme {
        val personViewModel: PersonViewModel = viewModel()
        MainScreen(personViewModel)
    }
}