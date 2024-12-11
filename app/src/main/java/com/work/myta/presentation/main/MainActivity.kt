package com.work.myta.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.presentation.authorized.record.RecordViewModel
import com.work.myta.ui.theme.MytaTheme
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppRepositoryImpl.initialize(this) // Передаем контекст приложения
        //  viewModel = ViewModelProvider.create(this).get(class:)
        setContent {
            val recordViewModel: RecordViewModel = viewModel()
            val mainviewModel: MainViewModel = viewModel()
            val logState by mainviewModel.logState.collectAsState()
            // Запрашиваем состояние авторизации
            // Отображаем соответствующий экран в зависимости от состояния
            MytaTheme {
                if (logState > 0){
                    Log.d("MainActivity", "logState: $logState")
                    AuthorizedMainScreen(recordViewModel)
                }else {
                    Log.d("MainActivity", "logState: $logState")
                    NotAuthorizedMainScreen(mainviewModel)}

            }
        }

    }
}
