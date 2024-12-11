package com.work.myta.presentation.main


import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.work.myta.presentation.authorized.record.RecordScreen
import com.work.myta.presentation.authorized.record.RecordViewModel
import com.work.myta.presentation.authorized.recording.AppotionsScreen
import com.work.myta.presentation.authorized.recording.CategoryScreen
import com.work.myta.presentation.authorized.recording.CheckYourInformationScreen
import com.work.myta.presentation.authorized.recording.ChoiceDateScreen
import com.work.myta.presentation.authorized.recording.MasterScreen
import com.work.myta.presentation.authorized.recording.RecordingScreen
import com.work.myta.presentation.authorized.recording.TypeScreen
import com.work.myta.presentation.navigation.AppAuthNavGraph

import com.work.myta.presentation.navigation.NavigationItem
import com.work.myta.presentation.navigation.rememberNavigationState
import com.work.myta.presentation.authorized.works.WorksScreen
import com.work.myta.presentation.navigation.Screen
import com.work.myta.ui.theme.main_app_color
import com.work.myta.ui.theme.secondary_app_color

@Composable
fun AuthorizedMainScreen(recordViewModel: RecordViewModel) {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
            ) {
                val navBackStackEntry = navigationState.navHostController
                    .currentBackStackEntryAsState()
                val items =
                    listOf(NavigationItem.Record, NavigationItem.Recording, NavigationItem.Works)
                items.forEach {
                    val selected =
                        navBackStackEntry.value?.destination?.hierarchy?.any { destination ->
                            it.screen.route == destination.route
                        } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = { if (!selected) navigationState.navigate(it.screen.route) },
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon), contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        },
                        label = {
                            Text(text = stringResource(id = it.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = main_app_color,
                            selectedTextColor = main_app_color,

                            //indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = secondary_app_color,
                            unselectedTextColor = secondary_app_color
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppAuthNavGraph(
            navHostController = navigationState.navHostController,
            worksScreenContent = { WorksScreen(paddingValues = paddingValues) },
            recordScreenContent = {
                RecordScreen(
                    paddingValues = paddingValues,
                    viewModel = recordViewModel
                )
            },
            recordingScreenContent = {
                RecordingScreen(
                    paddingValues = paddingValues,
                    onAppointmentClick = { navigationState.navigate(Screen.Type.route) },
                    recordViewModel = recordViewModel
                )
            },
            categoryScreenContent = {
                CategoryScreen(
                    paddingValues = paddingValues,
                    onManikuryaClick = {
                        navigationState.navigateToAppointment(
                            "service",
                            "Manikurya"
                        )
                    },
                    onPedikuryaClick = {
                        navigationState.navigateToAppointment(
                            "service",
                            "Pedikurya"
                        )
                    },
                    onSaleClick = {
                        navigationState.navigateToAppointment(
                            "service", "Sale"
                        )
                    })
            },
            typeScreenContent = {
                TypeScreen(
                    paddingValues = paddingValues,
                    onMansterClick = { navigationState.navigate(Screen.Master.route) },
                    onServiceClick = { navigationState.navigate(Screen.Category.route) })
            },
            masterScreenContent = {
                MasterScreen(
                    paddingValues = paddingValues,
                    onAnnaClick = { navigationState.navigateToAppointment("Master", "Anna") },
                    onEkaterinaClick = {
                        navigationState.navigateToAppointment(
                            "Master",
                            "Ekaterina"
                        )
                    },
                    onMariaClick = { navigationState.navigateToAppointment("Master", "Maria") }
                )
            },
            appointmentScreenContent = {

                val backStackEntry = navigationState.navHostController.currentBackStackEntry
                val type = backStackEntry?.arguments?.getString("type") // Извлекаем аргумент "type"
                val categoryOrMaster =
                    backStackEntry?.arguments?.getString("categoryOrMaster") // Извлекаем аргумент "categoryOrMaster"
                AppotionsScreen(
                    paddingValues = paddingValues,
                    type = type,
                    categoryOrMaster = categoryOrMaster,
                    recordViewModel = recordViewModel,
                ) { returnedValue ->
                    navigationState.navigateToChoiceDate(returnedValue)

                }
            },
            choiceDateScreenContent = {
                val backStackEntry = navigationState.navHostController.currentBackStackEntry
                val appotion =
                    backStackEntry?.arguments?.getString("appotion") // Извлекаем аргумент "appotion"

                ChoiceDateScreen(appotion!!, paddingValues, recordViewModel)
            },
            checkYourInformationScreenContent = {
                CheckYourInformationScreen(paddingValues, recordViewModel)
            }
        )
    }
}
