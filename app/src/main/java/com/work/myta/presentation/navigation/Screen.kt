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
    object Category : Screen(ROUTE_CATEGORY)
    object Type : Screen(ROUTE_TYPE)
    object Master : Screen(ROUTE_MASTER)

    object AppointmentRoute : Screen("$ROUTE_APPOINTMENT/{type}/{categoryOrMaster}") {
        fun createRoute(type: String, categoryOrMaster: String): String {
            return "$ROUTE_APPOINTMENT/$type/$categoryOrMaster"
        }
    }
    object ChoiceDate : Screen("$ROUTE_CHOICE_DATE/{appotion}"){
        fun createRoute(appotion: String): String {
            return "$ROUTE_CHOICE_DATE/$appotion"
        }
    }
    object CheckYourInformation : Screen(ROUTE_CHECK_YOUR_INFORMATION)



    private companion object {
        const val ROUTE_RECORDING = "recording"
        const val ROUTE_RECORD = "record"
        const val ROUTE_WORKS = "works"
        const val ROUTE_LOGIN = "login"
        const val ROUTE_SINGUP = "singup"
        const val ROUTE_NOT_AUTHORIZED = "notAuthorized"
        const val ROUTE_CATEGORY = "category"
        const val ROUTE_TYPE = "type"
        const val ROUTE_MASTER = "master"
        const val ROUTE_APPOINTMENT = "appointmentTo"
        const val ROUTE_CHOICE_DATE = "choiceDate"
        const val ROUTE_CHECK_YOUR_INFORMATION = "checkYourInformation"

    }
}

fun String.encode(): String {
    return Uri.encode(this)
}