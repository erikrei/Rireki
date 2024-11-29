package com.example.rireki.data.state

import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.enumclass.LIST_CREATE_ERROR

data class HomeUiState(
    val lists: List<ProfileList> = listOf(),
    val selectedListId: String = "",
    val newListName: String = "",
    val isOpenDialog: Boolean = false,
    val error: LIST_CREATE_ERROR = LIST_CREATE_ERROR.NONE
)