package com.work.myta.presentation.authorized.recording

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.ui.theme.ledger_regular_font


@Composable
fun TypeScreen(
    paddingValues: PaddingValues,
    onMansterClick: () -> Unit,
    onServiceClick: () -> Unit
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            CircleImageWithTextScreen(imageResource = R.drawable.service,
                text = "Запись на услугу",
                onClick = { onServiceClick() })
            CircleImageWithTextScreen(imageResource = R.drawable.master,
                text = "Запись к мастеру",
                onClick = { onMansterClick() })

        }

    }
}

@Composable
fun CircleImageWithTextScreen(
    imageResource: Int, // Resource ID for the image
    text: String,       // Text to display below the image
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Circular Image
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Set size for the circle
                .clip(CircleShape) // Clip into a circular shape
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { onClick() }, // Optional border
            contentScale = ContentScale.Crop // Scale image to fit the circle
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between image and text

        // Text Below the Image
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = ledger_regular_font,
            textAlign = TextAlign.Center
        )
    }
}
