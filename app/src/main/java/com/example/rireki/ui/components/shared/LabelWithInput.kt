package com.example.rireki.ui.components.shared

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.rireki.R

@Composable
fun LabelWithInput(
    @StringRes label: Int,
    labelColor: Color? = null,
    inputValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.label_with_input_spacing)
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = label),
            color = labelColor ?: Color.Unspecified
        )
        TextField(
            value = inputValue,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}