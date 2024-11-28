package com.example.rireki.data.state

import com.google.firebase.auth.FirebaseUser

data class UserUiState(
    val user: FirebaseUser? = null
)
