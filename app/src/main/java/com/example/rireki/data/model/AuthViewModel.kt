package com.example.rireki.data.model

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import com.example.rireki.data.enumclass.AUTH_ERROR
import com.example.rireki.data.enumclass.TOAST_TYPE
import com.example.rireki.data.state.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private fun checkInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            _uiState.update { it.copy(emailError = AUTH_ERROR.NO_INPUT) }
            return true
        }

        if (password.isEmpty()) {
            _uiState.update { it.copy(passwordError = AUTH_ERROR.NO_INPUT) }
            return true
        }

        if (password.length < 8) {
            _uiState.update { it.copy(passwordError = AUTH_ERROR.BAD_PASSWORD) }
            return true
        }

        return false
    }

    fun registerUser(
        auth: FirebaseAuth,
        email: String,
        password: String,
        coroutineScope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        successText: String,
        failureText: String
    ) {
        if (uiState.value.emailError != AUTH_ERROR.NONE || uiState.value.passwordError != AUTH_ERROR.NONE) this.resetErrors()
        if (this.checkInput(email, password)) return

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                this.setToastType(TOAST_TYPE.NONE)
                if (it.isSuccessful) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = successText,
                            withDismissAction = true
                        )
                    }
                } else {
                    this.setToastType(TOAST_TYPE.FAILURE)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = failureText,
                            withDismissAction = true
                        )
                    }
                }
            }
    }

    private fun resetErrors() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    emailError = AUTH_ERROR.NONE,
                    passwordError = AUTH_ERROR.NONE
                )
        }
    }

    private fun setToastType(toastType: TOAST_TYPE) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    toastType = toastType
                )
        }
    }

    fun loginUser(
        auth: FirebaseAuth,
        email: String,
        password: String,
        coroutineScope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        failureText: String,
        onSuccessLogin: () -> Unit,
    ) {
        if (uiState.value.emailError != AUTH_ERROR.NONE || uiState.value.passwordError != AUTH_ERROR.NONE) this.resetErrors()
        if (this.checkInput(email, password)) return

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccessLogin()
                } else {
                    this.setToastType(TOAST_TYPE.FAILURE)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = failureText,
                            withDismissAction = true,
                        )
                    }
                }
            }
    }

    fun updateEmailInput(emailInput: String) {
        _uiState.update {
            currentState -> currentState.copy(
                email = emailInput
            )
        }
    }

    fun updatePasswordInput(passwordInput: String) {
        _uiState.update {
            currentState -> currentState.copy(
                password = passwordInput
            )
        }
    }

    fun toggleIsLogin() {
        _uiState.update {
            currentState -> currentState.copy(
                email = "",
                password = "",
                isLogin = !uiState.value.isLogin,
                emailError = AUTH_ERROR.NONE,
                passwordError = AUTH_ERROR.NONE
            )
        }
    }
}