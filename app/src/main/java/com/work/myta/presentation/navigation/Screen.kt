package com.work.myta.presentation.navigation

import android.net.Uri


sealed class Screen(
    val route: String,
) {


    object Record : Screen(ROUTE_RECORD)
    object Recording : Screen(ROUTE_RECORDING)
    object Works : Screen(ROUTE_WORKS)
    object Login : Screen(ROUTE_LOGIN)
    object SingUp : Screen(ROUTE_SINGUP)
    object NotAuthorized : Screen(ROUTE_NOT_AUTHORIZED)


    private companion object {
        const val ROUTE_RECORDING = "recording"
        const val ROUTE_RECORD = "record"
        const val ROUTE_WORKS = "works"
        const val ROUTE_LOGIN = "login"
        const val ROUTE_SINGUP = "singup"
        const val ROUTE_NOT_AUTHORIZED = "notAuthorized"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}