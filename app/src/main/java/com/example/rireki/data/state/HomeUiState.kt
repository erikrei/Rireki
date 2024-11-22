package com.example.rireki.data.state

import com.example.rireki.data.ProfileList

data class HomeUiState(
    val lists: List<ProfileList> = listOf(),
    val newListName: String = "",
    val isOpenDialog: Boolean = false
)