package com.work.myta.presentation.notAuthorize.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import com.work.myta.ui.theme.ledger_regular_font
import com.work.myta.ui.theme.marck_script_regular_font


@Composable
fun NotAuthorizedScreen(paddingValues: PaddingValues, onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
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
                onClick = { onLoginClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff86C474)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
            ) {
                Text(text = "Вход",
                    fontSize = 30.sp,
                    fontFamily = ledger_regular_font)

            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { onRegisterClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff86C474)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
            ) {
                Text(text = "Регистрация",
                    fontSize = 30.sp,
                    fontFamily = ledger_regular_font)

            }

        }

    }
}

@Composable
fun IconWithText() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon), contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Мята",
                color = Color(0xFF378C1E),
                fontFamily = marck_script_regular_font,
                fontSize = 50.sp
            )
        }
    }

}