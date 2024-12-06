package com.work.myta.presentation.authorized.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.ui.theme.ledger_regular_font

@Composable
fun RecordScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = stringResource(R.string.my_recording),
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )

            Image(
                painter = painterResource(id = R.drawable.avatarka),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp) // Устанавливаем размер изображения
                    .clip(CircleShape) // Обрезаем в форму круга
                    .border(2.dp, Color.Gray, CircleShape), // Добавляем границу, если нужно
                contentScale = ContentScale.Crop // Устанавливаем масштабирование, чтобы заполнить круг
            )
            Text(
                text = "name",
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )


        }
    }

}