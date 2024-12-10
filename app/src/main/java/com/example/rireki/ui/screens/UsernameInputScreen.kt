package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.UsernameInputViewModel
import com.example.rireki.ui.components.UsernameInputButton
import com.example.rireki.ui.components.UsernameInputContainer

@Composable
fun UsernameInputScreen(
    onCompleteRegister: (String, String) -> Unit,
    usernameInputViewModel: UsernameInputViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val usernameInputUiState by usernameInputViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                dimensionResource(id = R.dimen.user_input_padding)
            )
    ) {
        UsernameInputContainer(
            firstName = usernameInputUiState.firstName,
            lastName = usernameInputUiState.lastName,
            usernameInputViewModel = usernameInputViewModel,
            modifier = Modifier
                .weight(1f)
        )
        UsernameInputButton(
            onClick = {
                onCompleteRegister(
                    usernameInputUiState.firstName,
                    usernameInputUiState.lastName
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}