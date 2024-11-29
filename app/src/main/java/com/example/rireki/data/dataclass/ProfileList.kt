package com.example.rireki.data.dataclass

import com.example.rireki.data.state.ListSettingsUiState
import java.time.LocalDate

data class ProfileList(
    val id: String = "",
    val name: String = "",
    val createdFrom: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val profiles: List<Profile> = listOf(),
    val settings: ListSettingsUiState = ListSettingsUiState(),
    val showRemoveProfile: Boolean = false,
    val removeProfile: String = "",
)