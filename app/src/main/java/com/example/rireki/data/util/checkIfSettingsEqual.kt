package com.example.rireki.data.util

import com.example.rireki.data.dataclass.ListSettings
import com.example.rireki.data.state.ListSettingsUiState

fun checkIfSettingsEqual(listName: String, s1: ListSettings, s2: ListSettingsUiState): Boolean {
    return listName == s2.newName && s1.privacy == s2.privacy && s1.admins == s2.admins
}