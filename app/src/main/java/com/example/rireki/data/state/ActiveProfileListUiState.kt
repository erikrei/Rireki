package com.example.rireki.data.state

import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.dataclass.ProfileList

data class ActiveProfileListUiState(
    val profileList: ProfileList = ProfileList(),
    val showRemoveProfile: Boolean = false,
    val removeProfile: Profile = Profile(),
)
