package com.work.myta.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.domain.entity.AuthState
import com.work.myta.ui.theme.MytaTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.work.myta.di.Component

class MainActivity : ComponentActivity() {


    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val component = getApplicationComponent()
            val component = remember { Component() }
            val viewModel: MainViewModel = viewModel(factory = component.getViewModelFactory())
            // Создаем ViewModel


            // Подписываемся на изменения состояния авторизации
            val authState by viewModel.authState.collectAsState(initial = AuthState.Initial)

            LaunchedEffect(Unit) {
                viewModel.checkAuthState()
            }

            // Отображаем соответствующий экран в зависимости от состояния
            MytaTheme {
                when (authState) {
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
