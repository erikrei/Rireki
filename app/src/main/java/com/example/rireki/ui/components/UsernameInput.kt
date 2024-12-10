package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.UsernameInputViewModel
import com.example.rireki.ui.components.shared.LabelWithInput

@Composable
fun UsernameInputContainer(
    usernameInputViewModel: UsernameInputViewModel = viewModel(),
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.user_input_component_spacing)
        ),
        modifier = modifier
    ) {
        UsernameInputHeader()
        UsernameInputDescription()
        LabelWithInput(
            label = R.string.user_input_first_name_label,
            inputValue = firstName,
            onValueChange = { usernameInputViewModel.changeFirstName(it) }
        )
        LabelWithInput(
            label = R.string.user_input_last_name_label,
            inputValue = lastName,
            onValueChange = { usernameInputViewModel.changeLastName(it) }
        )
    }
}

@Composable
fun UsernameInputHeader(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.user_input_header_text),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun UsernameInputDescription(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.user_input_description),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun UsernameInputButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.user_input_button_text)
        )
    }
}