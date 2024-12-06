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
import com.work.myta.presentation.navigation.AppAuthNavGraph

import com.work.myta.presentation.navigation.NavigationItem
import com.work.myta.presentation.navigation.rememberNavigationState
import com.work.myta.presentation.authorized.works.WorksScreen
import com.work.myta.ui.theme.main_app_color

@Composable
fun AuthorizedMainScreen() {
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
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppAuthNavGraph(
            navHostController = navigationState.navHostController,
            worksScreenContent = { WorksScreen(paddingValues = paddingValues) },
            recordScreenContent = {RecordScreen(paddingValues = paddingValues)},
            recordingScreenContent = {  },


        )
    }
}
