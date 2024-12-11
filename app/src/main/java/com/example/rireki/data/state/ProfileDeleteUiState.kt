package com.example.rireki.data.state

data class ProfileDeleteUiState(
    val showRemoveProfile: Boolean = false,
    val removeProfile: String = "",
    val userLeave: Boolean = false,
)