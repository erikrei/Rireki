package com.example.rireki.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.window.Dialog
import com.example.rireki.R
import com.example.rireki.data.enumclass.LIST_CREATE_ERROR

@Composable
fun HomeDialogAddList(
    listName: String,
    onListNameChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    error: LIST_CREATE_ERROR,
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
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                HomeDialogAddListField(
                    listName = listName,
                    onValueChange = onListNameChange,
                    error = error,
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
    error: LIST_CREATE_ERROR,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = listName,
            onValueChange = onValueChange,
            singleLine = true,
            isError = error != LIST_CREATE_ERROR.NONE,
            supportingText = {
                HomeDialogErrorMessage(
                    error = error
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.dialog_field_label),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.list_fill_24),
                    contentDescription = null,
                    tint = if (error != LIST_CREATE_ERROR.NONE)
                                MaterialTheme.colorScheme.error
                           else LocalContentColor.current
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun HomeDialogErrorMessage(
    error: LIST_CREATE_ERROR,
    modifier: Modifier = Modifier
) {
    if (error != LIST_CREATE_ERROR.NONE) {
        @StringRes val errorText: Int = when (error) {
            LIST_CREATE_ERROR.NAME_NOT_AVAILABLE -> R.string.dialog_list_not_available
            else -> R.string.empty
        }
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dialog_card_error_spacing)
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(
                    dimensionResource(id = R.dimen.dialog_card_error_padding)
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error_24), 
                contentDescription = null
            )
            Text(
                text = stringResource(id = errorText)
            )
        }
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