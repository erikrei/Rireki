package com.example.rireki.data.state

import com.example.rireki.data.enumclass.LIST_CREATE_ERROR
import com.example.rireki.data.dataclass.ProfileList

data class HomeUiState(
    val lists: List<ProfileList> = listOf(),
    val newListName: String = "",
    val isOpenDialog: Boolean = false,
    val error: LIST_CREATE_ERROR = LIST_CREATE_ERROR.NONE
)