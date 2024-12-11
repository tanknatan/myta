package com.work.myta.presentation.authorized.recording

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.work.myta.domain.entity.AppotionData
import com.work.myta.presentation.authorized.record.RecordViewModel
import com.work.myta.ui.theme.ledger_regular_font
import com.work.myta.ui.theme.main_app_color
import com.work.myta.ui.theme.secondary_app_color
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ChoiceDateScreen(appotion: String, paddingValues: PaddingValues, recordViewModel: RecordViewModel) {
    val appotionData = Json.decodeFromString<AppotionData>(appotion)

    val isSelected = remember { mutableStateOf(false) }


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
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            HorizontalCalendar(onClick = { day ->
                recordViewModel.appointmentDate.value = day
                isSelected.value = true
            },recordViewModel = recordViewModel)
            Image(
                painter = painterResource(id = appotionData.masterResId),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            // User Name
            Text(
                text = appotionData.master,
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )
            Text(
                text = "Мастер ногтевого сервиса",
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center, // Центрируем по горизонтали
                verticalAlignment = Alignment.CenterVertically // Центрируем по вертикали
            ) {
                Text(
                    text = appotionData.title,
                    fontFamily = ledger_regular_font,
                    fontSize = 20.sp,
                    color = Color.Black,
                )
            }



            Text(
                text = "${appotionData.time} минут/${appotionData.cost}₽",
                fontFamily = ledger_regular_font,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            if (isSelected.value) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { recordViewModel.appointmentTime.value = 10 }, colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.width(100.dp),
                        shape = ButtonDefaults
                            .shape
                    ) {

                        Text(text = "10:00", color = Color.Black)

                    }
                    Button(
                        onClick = { recordViewModel.appointmentTime.value = 12 }, colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.width(100.dp),
                        shape = ButtonDefaults
                            .shape
                    ) {

                        Text(text = "12:00", color = Color.Black)

                    }

                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { recordViewModel.appointmentTime.value = 14 }, colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.width(100.dp),
                        shape = ButtonDefaults
                            .shape
                    ) {

                        Text(text = "14:00", color = Color.Black)

                    }
                    Button(
                        onClick = { recordViewModel.appointmentTime.value = 16 }, colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.width(100.dp),
                        shape = ButtonDefaults
                            .shape
                    ) {

                        Text(text = "16:00", color = Color.Black)

                    }
                }
            }

        }
    }
}


@Composable
fun HorizontalCalendar(onClick: (Int) -> Unit,recordViewModel: RecordViewModel) {
    val currentDate = remember { Calendar.getInstance() }
    val today = currentDate.get(Calendar.DAY_OF_MONTH)
    val currentMonth = currentDate.get(Calendar.MONTH)
    val currentYear = currentDate.get(Calendar.YEAR)

    // Состояние для выбранной даты
    var selectedDate by remember { mutableStateOf<Int?>(null) }

    // Получаем текущий месяц и год
    val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(currentDate.time)

    // Получаем количество дней в текущем месяце
    val daysInMonth = recordViewModel.getDaysInMonth(currentMonth, currentYear)

    // Фильтруем дни, чтобы отображались только те, что не прошли
    val futureDays = daysInMonth.filter { it >= today }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Заголовок месяца и года
        Text(
            text = "$monthName $currentYear",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        // Горизонтальная прокрутка дней месяца
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(futureDays) { day ->
                DayItem(
                    day = day,
                    isToday = day == today,
                    isSelected = selectedDate == day,
                    onClick = {
                        selectedDate = day
                        onClick(day)
                    }, // Обработчик клика
                    month = currentMonth,
                    year = currentYear
                )
            }
        }
    }
}

@Composable
fun DayItem(
    day: Int,
    isToday: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    month: Int,
    year: Int
) {
    // Получаем день недели для текущего дня
    val calendar = Calendar.getInstance().apply {
        set(year, month, day)
    }
    val dayOfWeek = SimpleDateFormat(
        "E",
        Locale.getDefault()
    ).format(calendar.time) // Например "Mon" для понедельника

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .background(
                when {
                    isSelected -> main_app_color
                    isToday -> secondary_app_color
                    else -> Color.Transparent
                },
                shape = CircleShape
            ) // Цвет фона для выделения
            .padding(8.dp)
            .clickable(onClick = onClick) // Обработчик клика по дню
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayOfWeek, // Название дня недели
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = day.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) Color.White else Color.Black // Цвет текста для выбранного дня
            )
        }
    }
}




