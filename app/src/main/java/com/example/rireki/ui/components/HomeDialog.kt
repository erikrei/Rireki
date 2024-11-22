package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.rireki.R

@Composable
fun HomeDialogAddList(
    listName: String,
    onListNameChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.dialog_card_inner_padding)
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                HomeDialogAddListField(
                    listName = listName,
                    onValueChange = onListNameChange
                )
                HomeDialogAddListButtons(
                    onSubmit = onSubmit,
                    onDismissRequest = onDismissRequest,
                    listName = listName,
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
        TextField(
            value = listName,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.dialog_field_label),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.list_fill_24),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(0.dp)
        )
    }
}

@Composable
fun HomeDialogAddListButtons(
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    listName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TextButton(
            onClick = onDismissRequest
        ) {
            Text(
                text = stringResource(id = R.string.dialog_cancel_button).uppercase(),
                color = MaterialTheme.colorScheme.error,
            )
        }
        TextButton(
            onClick = onSubmit,
            enabled = listName.isNotEmpty()
        ) {
            Text(
                text = stringResource(id = R.string.dialog_submit_button).uppercase()
            )
        }
    }
}