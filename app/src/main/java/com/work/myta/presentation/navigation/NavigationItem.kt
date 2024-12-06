package com.work.myta.presentation.navigation

import com.work.myta.R


sealed class NavigationItem(
    val titleResId: Int,
    val icon: Int,
    val screen: Screen
) {
    object Record : NavigationItem(
        titleResId = R.string.record,
        icon = R.drawable.my_record,
        screen = Screen.Record
    )
    object Recording : NavigationItem(
        titleResId = R.string.recording,
        icon = R.drawable.recording,
        screen = Screen.Recording
    )
    object Works : NavigationItem(
        titleResId = R.string.works,
        icon = R.drawable.works,
        screen = Screen.Works
    )



}
