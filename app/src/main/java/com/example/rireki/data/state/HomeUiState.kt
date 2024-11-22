package com.example.rireki.data.state

import com.example.rireki.data.objects.ProfileList

data class HomeUiState(
    val lists: List<ProfileList> = listOf()
)