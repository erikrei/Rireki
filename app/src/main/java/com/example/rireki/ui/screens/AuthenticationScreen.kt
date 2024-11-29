package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.AuthViewModel
import com.example.rireki.ui.components.AuthFooter
import com.example.rireki.ui.components.AuthHeader
import com.example.rireki.ui.components.AuthSubmitButton
import com.example.rireki.ui.components.AuthTextField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthenticationScreen(
    auth: FirebaseAuth,
    onSuccessLogin: () -> Unit,
    authViewModel: AuthViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val authUiState by authViewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val successTextRegister = stringResource(id = R.string.auth_register_success, authUiState.email)
    val failureTextRegister = stringResource(id = R.string.auth_register_failure)
    val failureTextLogin = stringResource(id = R.string.auth_login_failure)

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        modifier = modifier
    ) {
        paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.auth_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.auth_vertical_padding)
                    )
            ) {
                AuthHeader(
                    isLogin = authUiState.isLogin
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AuthTextField(
                        value = authUiState.email,
                        onValueChange = { authViewModel.updateEmailInput(it) },
                        label = R.string.email_label,
                        fieldIcon = R.drawable.mail_24,
                        error = authUiState.emailError
                    )
                    AuthTextField(
                        value = authUiState.password,
                        onValueChange = { authViewModel.updatePasswordInput(it) },
                        label = R.string.password_label,
                        fieldIcon = R.drawable.key_24,
                        error = authUiState.passwordError
                    )
                    AuthSubmitButton(
                        isLogin = authUiState.isLogin,
                        onClick = {
                            if (authUiState.isLogin) {
                                authViewModel.loginUser(
                                    auth = auth,
                                    email = authUiState.email,
                                    password = authUiState.password,
                                    onSuccessLogin = onSuccessLogin,
                                    coroutineScope = scope,
                                    snackbarHostState = snackbarHostState,
                                    failureText = failureTextLogin
                                )
                            } else {
                                authViewModel.registerUser(
                                    auth = auth,
                                    email = authUiState.email,
                                    password = authUiState.password,
                                    coroutineScope = scope,
                                    snackbarHostState = snackbarHostState,
                                    successText = successTextRegister,
                                    failureText = failureTextRegister
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                AuthFooter(
                    isLogin = authUiState.isLogin,
                    onAuthSwitch = { authViewModel.toggleIsLogin() }
                )
            }
    }
}

