package com.work.myta.presentation.authorized.works

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.work.myta.R

@Composable
fun WorksScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        //contentAlignment = Alignment.BottomCenter

    ) {
        Image(
            painter = painterResource(id = R.drawable.background), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            ScrollableImage()


        }

    }

}

@Composable
fun ScrollableImage() {
    // Создаем состояние для прокрутки
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize() // Занимает все доступное пространство
            .verticalScroll(scrollState) // Вертикальная прокрутка
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "Scrollable Image",
            contentScale = ContentScale.FillWidth, // Указывает, как изображение заполняет контейнер
            modifier = Modifier.fillMaxWidth() // Занимает всю ширину
        )
    }
}