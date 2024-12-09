package com.example.rireki.data.state

import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.dataclass.UserInformation

data class UserUiState(
    val userInfo: UserInformation = UserInformation(),
    val userData: List<ProfileList> = listOf()
)
