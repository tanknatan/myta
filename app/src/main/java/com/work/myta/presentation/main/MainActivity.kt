package com.work.myta.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.work.myta.domain.entity.AuthState
import com.work.myta.ui.theme.MytaTheme
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authState : MutableState<AuthState> = remember {
                mutableStateOf(AuthState.NotAuthorized)
            }

            // Отображаем соответствующий экран в зависимости от состояния
            MytaTheme {
                when (authState.value) {
                    AuthState.Authorized -> {
                        AuthorizedMainScreen()
                    }

                    AuthState.Initial -> {

                        // Здесь можно показать загрузочный индикатор

                    }

                    AuthState.NotAuthorized -> {
                        NotAuthorizedMainScreen()
                    }
                }
            }
        }
    }

}
