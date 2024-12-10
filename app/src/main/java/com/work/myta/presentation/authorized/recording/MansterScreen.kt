package com.work.myta.presentation.authorized.recording

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R


@Composable
fun MasterScreen(
    paddingValues: PaddingValues,
    onAnnaClick: () -> Unit,
    onEkaterinaClick: () -> Unit,
    onMariaClick: () -> Unit

) {

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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            MasterCard(imageRes = R.drawable.anna, name = "Анна", description ="Мастер ногтевого сервиса", onMasterClick ={ onAnnaClick() })
            MasterCard(imageRes = R.drawable.ekaterina, name = "Екатерина", description ="Мастер ногтевого сервиса",onMasterClick = {onEkaterinaClick()} )
            MasterCard(imageRes = R.drawable.maria, name = "Мария", description ="Мастер ногтевого сервиса" ,onMasterClick ={onMariaClick()} )




        }

    }
}

@Composable
fun MasterCard(
    imageRes: Int, // Ресурс изображения
    name: String,  // Имя мастера
    description: String, // Описание мастера
    onMasterClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onMasterClick() },
        border = BorderStroke(1.dp, Color.Blue) // Синяя рамка
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Круглое изображение мастера
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp) // Размер круга
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape), // Серая граница
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Текстовая часть
            Column {
                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
