package com.work.myta.presentation.main

import androidx.navigation.NavGraphBuilder
import android.os.Build
import androidx.compose.runtime.Composable

import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.work.myta.presentation.authorized.record.RecordScreen
import com.work.myta.presentation.navigation.Screen


fun NavGraphBuilder.recordScreenNavGraph(
    recordingScreenContetn: @Composable () -> Unit,
    categoryScreenContent: @Composable () -> Unit,
    typeScreenContent: @Composable () -> Unit,
    masterScreenContent: @Composable () -> Unit,
    appointmentScreenContent: @Composable () -> Unit,
    choiceDateScreenContent: @Composable () -> Unit
) {
    navigation(
        route = "recordingGraph", // Уникальный маршрут для графа навигации
        startDestination = Screen.Recording.route // Стартовое назначение
    ) {
        composable(Screen.Recording.route) {
            recordingScreenContetn()
        }
        composable(Screen.Category.route) {
            categoryScreenContent()
        }
        composable(Screen.Type.route) {
            typeScreenContent()
        }
        composable(Screen.Master.route) {
            masterScreenContent()
        }
        composable(Screen.AppointmentRoute.route) {
            appointmentScreenContent()
        }
        composable(Screen.ChoiceDate.route) {
            choiceDateScreenContent()
        }
    }
}
