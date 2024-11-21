package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.rireki.R

@Composable
fun HomeDialogAddList(
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var listName by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.dialog_card_inner_spacing)
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.dialog_card_inner_padding)
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                HomeDialogAddListField(
                    listName = listName,
                    onValueChange = { listName = it }
                )
                HomeDialogAddListButtons(
                    onSubmit = onSubmit,
                    onDismissRequest = onDismissRequest,
                    modifier = Modifier
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun HomeDialogAddListField(
    listName: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = listName,
            onValueChange = onValueChange,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.dialog_field_label),
                )
            }
        )
    }
}

@Composable
fun HomeDialogAddListButtons(
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TextButton(
            onClick = onDismissRequest
        ) {
            Text(
                text = stringResource(id = R.string.dialog_cancel_button),
                color = MaterialTheme.colorScheme.error
            )
        }
        TextButton(
            onClick = onSubmit
        ) {
            Text(
                text = stringResource(id = R.string.dialog_submit_button)
            )
        }
    }
}