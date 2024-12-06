package com.work.myta.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.work.myta.domain.entity.Country
import com.work.myta.ui.theme.ledger_regular_font


@Composable
fun CountryPicker(
    selectedCountry: Country,

) {


    Box(
        modifier = Modifier
            .padding(start = 8.dp)

    ) {
        Text(
            text = "${selectedCountry.flag} ${selectedCountry.dialCode}",
            fontFamily = ledger_regular_font,
        )

    }
}