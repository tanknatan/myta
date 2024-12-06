package com.work.myta.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun BlueTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    //textStyle: TextStyle = Theme.typography.textInput,
    label: @Composable (() -> Unit)? = null,
    placeholderText: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },

    colors: TextFieldColors = TextFieldDefaults.colors(


        disabledTextColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Black, // Цвет линии в фокусе
        unfocusedIndicatorColor = Color.Gray, // Цвет линии без фокуса
        disabledIndicatorColor = Color.LightGray, // Цвет линии, когда поле отключено
        errorIndicatorColor = Color.Red, // Цвет линии в случае ошибки
        errorContainerColor = Color.Transparent,
//    errorCursorColor: Color = Color.Transparent,
//    selectionColors: TextSelectionColors? = null,
//    focusedIndicatorColor: Color = Color.Unspecified,
//    unfocusedIndicatorColor: Color = Color.Unspecified,
//    disabledIndicatorColor: Color = Color.Unspecified,
//    errorIndicatorColor: Color = Color.Unspecified,
//    focusedLeadingIconColor: Color = Color.Unspecified,
//    unfocusedLeadingIconColor: Color = Color.Unspecified,
//    disabledLeadingIconColor: Color = Color.Unspecified,
//    errorLeadingIconColor: Color = Color.Unspecified,


    )
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        // textStyle = textStyle,
        label = label,
        placeholder = {
            placeholder?.invoke() ?: placeholderText?.let {
                Text(
                    text = it,
                    // style = Theme.typography.textInput
                )
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        //shape = shape,
        colors = colors
    )

}