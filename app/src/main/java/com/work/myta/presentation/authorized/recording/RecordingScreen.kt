package com.work.myta.presentation.authorized.recording

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.presentation.navigation.Screen
import com.work.myta.presentation.notAuthorize.welcome.IconWithText
import com.work.myta.ui.theme.ledger_regular_font

@Composable
fun RecordingScreen(paddingValues: PaddingValues, onAppointmentClick: () -> Unit) {

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconWithText()
            }
            Text(text = "СТУДИЯ МАНИКЮРА",
                fontSize = 30.sp,
                fontFamily = ledger_regular_font,)
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = { onAppointmentClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff86C474)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
            ) {
                Text(text = "Записаться",
                    fontSize = 30.sp,
                    fontFamily = ledger_regular_font
                )

            }
            Spacer(modifier = Modifier.height(30.dp))


        }

    }
}