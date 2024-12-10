package com.work.myta.presentation.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.work.myta.R

import com.work.myta.presentation.notAuthorize.welcome.NotAuthorizedScreen
import com.work.myta.presentation.notAuthorize.login.LoginScreen
import com.work.myta.presentation.navigation.AppNotAuthNavGraph
import com.work.myta.presentation.navigation.Screen
import com.work.myta.presentation.navigation.rememberNavigationState
import com.work.myta.presentation.notAuthorize.singup.SignUpScreen


import com.work.myta.ui.theme.ledger_regular_font
import com.work.myta.ui.theme.marck_script_regular_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotAuthorizedMainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()
    Scaffold(
        topBar = {


            val navBackStackEntry = navigationState.navHostController
                .currentBackStackEntryAsState()

            // Проверка, что текущий экран не является Screen.NotAuthorized
            val isNotAuthorizedRoute =
                navBackStackEntry.value?.destination?.hierarchy?.none { destination ->
                    destination.route == Screen.NotAuthorized.route
                } ?: true

            // Показываем TopAppBar только если текущий экран не является NotAuthorized
            if (isNotAuthorizedRoute) {

                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                fontFamily = ledger_regular_font,
                                modifier = Modifier.offset(-30.dp, 0.dp),
                                fontSize = 30.sp
                            )
                        }
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable { navigationState.navigate(Screen.NotAuthorized.route) }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xff86C474),
                        scrolledContainerColor = Color.Transparent,
                        //navigationIconContentColor = Color.White, // Настройка цвета иконки
                        //titleContentColor = Color.White          // Настройка цвета заголовка
                    )
                )

            }
        },
    ) { paddingValues ->
        AppNotAuthNavGraph(
            navHostController = navigationState.navHostController,


            loginScreenContent = {
                LoginScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            },
            singupScreenContent = {
                SignUpScreen(
                    paddingValues,
                    onSeccess = { navigationState.navigate(Screen.Login.route) })
            },
            notauthorizedScreenContent = {
                NotAuthorizedScreen(
                    paddingValues,
                    onRegisterClick = { navigationState.navigate(Screen.SingUp.route) },
                    onLoginClick = { navigationState.navigate(Screen.Login.route) })
            }
        )
    }
}