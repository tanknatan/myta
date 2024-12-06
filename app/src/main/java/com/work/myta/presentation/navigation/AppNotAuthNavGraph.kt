package com.work.myta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNotAuthNavGraph(
    navHostController: NavHostController,
    loginScreenContent: @Composable () -> Unit,
    singupScreenContent: @Composable () -> Unit,
    notauthorizedScreenContent: @Composable () -> Unit
) {
    NavHost(navController = navHostController, startDestination = Screen.NotAuthorized.route) {


        composable(Screen.NotAuthorized.route) {
            notauthorizedScreenContent()
        }

        composable(Screen.Login.route) {
            loginScreenContent()
        }
        composable(Screen.SingUp.route) {
            singupScreenContent()
        }

    }
}

