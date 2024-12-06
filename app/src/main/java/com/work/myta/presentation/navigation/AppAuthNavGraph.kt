package com.work.myta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppAuthNavGraph(
    navHostController: NavHostController,

    recordScreenContent: @Composable () -> Unit,
    recordingScreenContent: @Composable () -> Unit,
    worksScreenContent: @Composable () -> Unit,

) {
    NavHost(navController = navHostController, startDestination = Screen.Record.route) {



        composable(Screen.Record.route) {
            recordScreenContent()
        }
        composable(Screen.Recording.route) {
            recordingScreenContent()
        }
        composable(Screen.Works.route) {
            worksScreenContent()
        }

    }
}

