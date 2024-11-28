package com.example.rireki.data.util

import com.example.rireki.data.state.ListSettingsUiState

fun checkIfSettingsEqual(s1: ListSettingsUiState, s2: ListSettingsUiState): Boolean {
    return s1.listName == s2.listName && s1.listPrivacy == s2.listPrivacy && s1.admins == s2.admins
}