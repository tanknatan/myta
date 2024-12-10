package com.work.myta.presentation.authorized.recording

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.domain.entity.AppotionData
import com.work.myta.ui.theme.ledger_regular_font
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ChoiceDateScreen(appotion: String, paddingValues: PaddingValues) {
    val appotionData = Json.decodeFromString<AppotionData>(appotion)
    var selectedDate by remember { mutableStateOf<Int>(0) }

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
            HorizontalCalendar(onClick = {day ->
                selectedDate = day

            })
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
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {

                }
                Button(onClick = { /*TODO*/ }) {

                }

            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {

                }
                Button(onClick = { /*TODO*/ }) {

                }

            }


        }

    }

}



@Composable
fun HorizontalCalendar(onClick: (Int) -> Unit) {
    val currentDate = remember { Calendar.getInstance() }
    val today = currentDate.get(Calendar.DAY_OF_MONTH)
    val currentMonth = currentDate.get(Calendar.MONTH)
    val currentYear = currentDate.get(Calendar.YEAR)

    // Состояние для выбранной даты
    var selectedDate by remember { mutableStateOf<Int?>(null) }

    // Получаем текущий месяц и год
    val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(currentDate.time)

    // Получаем количество дней в текущем месяце
    val daysInMonth = getDaysInMonth(currentMonth, currentYear)

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

        // Дни недели
        DaysOfWeek()

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
                    onClick = { selectedDate = day
                              onClick(day)}, // Обработчик клика
                    month = currentMonth,
                    year = currentYear
                )
            }
        }
    }
}

@Composable
fun DaysOfWeek() {
    val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
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
    val dayOfWeek = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time) // Например "Mon" для понедельника

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )
            .background(
                when {
                    isSelected -> Color.Cyan
                    isToday -> Color.LightGray
                    else -> Color.Transparent
                }
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

fun getDaysInMonth(month: Int, year: Int): List<Int> {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)

    // Получаем количество дней в текущем месяце
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    return (1..daysInMonth).toList()
}


