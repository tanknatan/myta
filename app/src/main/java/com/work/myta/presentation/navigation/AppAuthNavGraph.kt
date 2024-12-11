package com.work.myta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.work.myta.presentation.main.recordScreenNavGraph


@Composable
fun AppAuthNavGraph(
    navHostController: NavHostController,
    recordScreenContent: @Composable () -> Unit,
    recordingScreenContent: @Composable () -> Unit,
    worksScreenContent: @Composable () -> Unit,
    categoryScreenContent: @Composable () -> Unit,
    typeScreenContent: @Composable () -> Unit,
    masterScreenContent: @Composable () -> Unit,
    appointmentScreenContent: @Composable () -> Unit,
    choiceDateScreenContent: @Composable () -> Unit,
    checkYourInformationScreenContent: @Composable () -> Unit

) {
    NavHost(navController = navHostController, startDestination = Screen.Record.route) {
        composable(Screen.Record.route) {
            recordScreenContent()
        }
        recordScreenNavGraph(
            recordingScreenContetn = recordingScreenContent,
            categoryScreenContent = categoryScreenContent,
            typeScreenContent = typeScreenContent,
            masterScreenContent = masterScreenContent,
            appointmentScreenContent = appointmentScreenContent,
            choiceDateScreenContent = choiceDateScreenContent,
            checkYourInformationScreenContent = checkYourInformationScreenContent

        )

        composable(Screen.Works.route) {
            worksScreenContent()
        }

    }
}

