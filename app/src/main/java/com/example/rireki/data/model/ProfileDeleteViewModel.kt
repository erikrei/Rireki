package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.ProfileDeleteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileDeleteViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ProfileDeleteUiState())
    val uiState: StateFlow<ProfileDeleteUiState> = _uiState.asStateFlow()

    fun setProfileToRemoveFromList(listId: String, profileName: String) {
        _uiState.update { it.copy(showRemoveProfile = true, removeProfile = profileName) }
    }

    fun unsetShowProfileRemove() {
        _uiState.update { it.copy(showRemoveProfile = false, removeProfile = "") }
    }
}