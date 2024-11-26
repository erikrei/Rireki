package com.example.rireki.ui.components.shared

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ConfirmAlert(
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    @StringRes title: Int,
    @StringRes confirmText: Int,
    @StringRes dismissText: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmRequest
            ) {
                Text(
                    text = stringResource(id = confirmText).uppercase(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(id = dismissText).uppercase()
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = title)
            )
        },
        text = {
            Text(
                text = text
            )
        },
        modifier = modifier
    )
}