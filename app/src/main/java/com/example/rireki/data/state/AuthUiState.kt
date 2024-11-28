package com.example.rireki.data.state

import com.example.rireki.data.enumclass.AUTH_ERROR

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLogin: Boolean = true,
    val emailError: AUTH_ERROR = AUTH_ERROR.NONE,
    val passwordError: AUTH_ERROR = AUTH_ERROR.NONE
)
