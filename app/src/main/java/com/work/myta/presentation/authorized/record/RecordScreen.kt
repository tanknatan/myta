package com.work.myta.presentation.authorized.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.domain.entity.Appointment
import com.work.myta.ui.theme.ledger_regular_font

@Composable
fun RecordScreen(
    viewModel: RecordViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    paddingValues: PaddingValues
) {
    val user by viewModel.user.collectAsState() // Current user
    val appointments by viewModel.appointments.collectAsState(emptyList()) // Appointments for the user

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title
            Text(
                text = stringResource(R.string.my_recording),
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )

            // User Avatar
            Image(
                painter = painterResource(id = R.drawable.avatarka),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            // User Name
            Text(
                text = user?.name ?: "",
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(paddingValues),
                contentAlignment = Alignment.Center

            ) {
                if(appointments.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(appointments) { appointment ->
                        AppointmentItem(appointment)
                    }
                }
            }else Text(
                text = stringResource(R.string.no_records),
                fontFamily = ledger_regular_font,
                fontSize = 30.sp,
                color = Color.Black
            )
            }
        }
    }
}

@Composable
fun AppointmentItem(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.avatarka), // Replace with your icon
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Type: ${appointment.type}",

                    color = Color.Black
                )
                Text(
                    text = "Date: ${appointment.appointmentDate}",

                    color = Color.Gray
                )
            }
        }
    }
}



