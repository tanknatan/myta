package com.work.myta.presentation.authorized.recording

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.work.myta.R
import com.work.myta.domain.entity.AnnaData
import com.work.myta.domain.entity.AppotionData
import com.work.myta.presentation.main.MainViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun AppotionsScreen(
    paddingValues: PaddingValues,
    type: String?,
    categoryOrMaster: String?,
    onAppotionClick: (String) -> Unit
) {
    val viewModel: RecordingScreenViewModel = viewModel()
    val serviceCards = viewModel.foundArray(type, categoryOrMaster)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        //contentAlignment = Alignment.BottomCenter

    ) {
        Image(
            painter = painterResource(id = R.drawable.background), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(serviceCards) { appotion ->
                val serviceJson = Json.encodeToString(appotion)
                ServiceCardScreen(appotion = appotion, onClick = {onAppotionClick(serviceJson) })
            }
        }

    }
}

@Composable
fun ServiceCardScreen(appotion: AppotionData, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
            .clickable { onClick() },
    ) {
        // Title
        Text(
            text = appotion.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Description
        Text(
            text = appotion.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Duration and Price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${appotion.time} мин",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "₽ ${appotion.cost}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}