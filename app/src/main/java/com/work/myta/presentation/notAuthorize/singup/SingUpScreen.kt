package com.work.myta.presentation.notAuthorize.singup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.work.myta.R
import com.work.myta.domain.entity.Country
import com.work.myta.ui.BlueTextField
import com.work.myta.ui.CountryPicker
import com.work.myta.ui.MaskVisualTransformation
import com.work.myta.ui.checkMask
import com.work.myta.ui.filterWithMask
import com.work.myta.ui.theme.ledger_regular_font

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SignUpScreen(paddingValues: PaddingValues,onSeccess:()->Unit) {

    val viewModel: SignUpViewModel = viewModel()
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") } // Локальная переменная для проверки кода
    var isNameError by remember { mutableStateOf(false) }
    var isPhoneError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var isConfirmPasswordError by remember { mutableStateOf(false) }
    var isSnackbarVisible by remember { mutableStateOf(false) } // Для управления отображением Snackbar
    var generatedCode by remember { mutableStateOf("") } // Для отображения кода
    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }

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
                text = stringResource(R.string.please_introduce_yourself),
                fontFamily = ledger_regular_font,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            // Поля ввода
            TextField(
                value = name,
                onValueChange = { name = it; isNameError = false },
                label = { Text("Имя") },
                isError = isNameError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (isNameError) Color.Red else Color.Black,
                    unfocusedIndicatorColor = if (isNameError) Color.Red else Color.Gray
                )
            )

            BlueTextField(
                value = phone,
                onValueChange = {
                    phone = it.filterWithMask(selectedCountry.phoneMask)
                    isPhoneError = false
                },
                placeholderText = selectedCountry.phoneMask,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                isError = isPhoneError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                visualTransformation = maskVisualTransformation,
                leadingIcon = {
                    CountryPicker(selectedCountry = selectedCountry)
                }
            )

            TextField(
                value = email,
                onValueChange = { email = it; isEmailError = false },
                label = { Text("Электронная почта") },
                isError = isEmailError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (isEmailError) Color.Red else Color.Black,
                    unfocusedIndicatorColor = if (isEmailError) Color.Red else Color.Gray
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it; isPasswordError = false; isConfirmPasswordError = false },
                label = { Text("Пароль") },
                isError = isPasswordError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (isPasswordError) Color.Red else Color.Black,
                    unfocusedIndicatorColor = if (isPasswordError) Color.Red else Color.Gray
                )
            )

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it; isConfirmPasswordError = false },
                label = { Text("Повторите пароль") },
                isError = isConfirmPasswordError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (isConfirmPasswordError) Color.Red else Color.Black,
                    unfocusedIndicatorColor = if (isConfirmPasswordError) Color.Red else Color.Gray
                )
            )

            Button(
                onClick = {
                    val isValid = validateInputs(
                        name = name,
                        phone = phone,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        isNameErrorSetter = { isNameError = it },
                        isPhoneErrorSetter = { isPhoneError = it },
                        isEmailErrorSetter = { isEmailError = it },
                        isPasswordErrorSetter = { isPasswordError = it },
                        isConfirmPasswordErrorSetter = { isConfirmPasswordError = it }
                    )

                    if (isValid) {
                        verificationCode = viewModel.generateVerificationCode() // Генерация кода
                        println("Сгенерированный код: $verificationCode") // Для отладки (в реальном приложении отправляется через SMS)
                        showDialog = true // Показ диалогового окна
                        isSnackbarVisible = true // Показываем Snackbar
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff86C474)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp),
            ) {
                Text(
                    text = "Регистрация",
                    fontSize = 30.sp,
                    fontFamily = ledger_regular_font
                )
            }
        }
        if (isSnackbarVisible) {
            Snackbar(
                action = {
                    TextButton(onClick = { isSnackbarVisible = false }) {
                        Text(text = "Закрыть")
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Log.d(generatedCode.toString(), verificationCode.toString())
                Text(text = "Ваш код подтверждения: $generatedCode",
                    color = Color.Red)
            }
        }
        // Диалоговое окно для ввода кода
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Введите код подтверждения") },
                text = {
                    TextField(
                        value = dialogText,
                        onValueChange = { dialogText = it },
                        label = { Text(text = "Код") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray
                        )
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        if (dialogText == verificationCode) {
                            viewModel.saveUserData(name, phone, email, password)
                            onSeccess()
                        }
                        showDialog = false
                    }) {
                        Text("Подтвердить")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}


// Функция проверки ввода
private fun validateInputs(
    name: String,
    phone: String,
    email: String,
    password: String,
    confirmPassword: String,
    isNameErrorSetter: (Boolean) -> Unit,
    isPhoneErrorSetter: (Boolean) -> Unit,
    isEmailErrorSetter: (Boolean) -> Unit,
    isPasswordErrorSetter: (Boolean) -> Unit,
    isConfirmPasswordErrorSetter: (Boolean) -> Unit
): Boolean {
    var isValid = true

    if (name.isBlank()) {
        isNameErrorSetter(true)
        isValid = false
    }

    if (phone.isBlank()) {
        isPhoneErrorSetter(true)
        isValid = false
    }

    if (email.isBlank()) {
        isEmailErrorSetter(true)
        isValid = false
    }

    if (password.isBlank()) {
        isPasswordErrorSetter(true)
        isValid = false
    }

    if (confirmPassword.isBlank() || confirmPassword != password) {
        isConfirmPasswordErrorSetter(true)
        isValid = false
    }

    return isValid
}

