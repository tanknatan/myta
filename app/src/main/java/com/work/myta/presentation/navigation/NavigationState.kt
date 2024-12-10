package com.work.myta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


class NavigationState(
    val navHostController: NavHostController
) {

    fun navigate(route: String) {
        navHostController.navigate(route) {
            popUpTo(id = navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToAppointment(type: String, categoryOrMaster: String) {
        navHostController.navigate(Screen.AppointmentRoute.createRoute(type, categoryOrMaster))
    }
    fun navigateToChoiceDate(appotion : String) {
        navHostController.navigate(Screen.ChoiceDate.createRoute(appotion))
    }


}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}

