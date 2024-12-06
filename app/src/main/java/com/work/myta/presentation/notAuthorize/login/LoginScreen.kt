package com.work.myta.presentation.notAuthorize.login

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.myta.R
import com.work.myta.domain.entity.Country
import com.work.myta.ui.BlueTextField
import com.work.myta.ui.CountryPicker
import com.work.myta.ui.MaskVisualTransformation
import com.work.myta.ui.checkMask
import com.work.myta.ui.filterWithMask
import com.work.myta.ui.theme.ledger_regular_font

import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LoginScreen(paddingValues: PaddingValues, onRegisterClick: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val selectedCountry by remember { mutableStateOf(Country.RUSSIA) }
    val maskVisualTransformation by remember {
        derivedStateOf {
            MaskVisualTransformation(selectedCountry.phoneMask)
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),


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
                text = stringResource(R.string.enter_your_phone_number),
                fontFamily = ledger_regular_font,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )


            BlueTextField(
                value = phone,
                onValueChange = {
                    phone = it.filterWithMask(selectedCountry.phoneMask)
                },
                placeholderText = selectedCountry.phoneMask,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (phone.checkMask(selectedCountry.phoneMask)) {
                            // onSendPhone(phone)
                            keyboardController?.hide()
                            focusRequester.clearFocus()
                        }
                    }),
                //isError = isError,
                visualTransformation = maskVisualTransformation,
                leadingIcon = {
                    CountryPicker(
                        selectedCountry = selectedCountry,

                        )
                }
            )
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
//                placeholderText = selectedCountry.phoneMask,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        if (phone.checkMask(selectedCountry.phoneMask)) {
//                            // onSendPhone(phone)
//                            keyboardController?.hide()
//                            focusRequester.clearFocus()
//                        }
//                    }),
                //isError = isError,
                colors = TextFieldDefaults.colors(


                    disabledTextColor = Color.Transparent,
                    unfocusedTextColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black, // Цвет линии в фокусе
                    unfocusedIndicatorColor = Color.Gray, // Цвет линии без фокуса
                    disabledIndicatorColor = Color.LightGray, // Цвет линии, когда поле отключено
                    errorIndicatorColor = Color.Red // Цвет линии в случае ошибки


                ),

                )
            Button(
                onClick = {
                    //onSendPhone("${selectedCountry.dialCode}$phone")
                    keyboardController?.hide()
                    focusRequester.clearFocus()
                },
                //enabled = !isLoading && phone.checkMask(selectedCountry.phoneMask),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff86C474)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp),

                ) {
                Text(
                    text = "Войти",
                    fontSize = 30.sp,
                    fontFamily = ledger_regular_font
                )

            }
        }
    }
}



