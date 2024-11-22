package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

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
            )
        }
    }
}