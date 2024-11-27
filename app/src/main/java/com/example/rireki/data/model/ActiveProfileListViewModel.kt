package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.state.ActiveProfileListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ActiveProfileListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ActiveProfileListUiState())
    val uiState: StateFlow<ActiveProfileListUiState> = _uiState.asStateFlow()

    fun setActiveProfileList(profileList: ProfileList) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    profileList = profileList
                )
        }
    }

    fun setRemoveProfile(profile: Profile) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    showRemoveProfile = true,
                    removeProfile = profile
                )
        }
    }

    fun unsetRemoveProfile() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    showRemoveProfile = false,
                    removeProfile = Profile()
                )
        }
    }

    fun removeProfile() {
        val profileToRemove: Profile = uiState.value.removeProfile
        val newProfiles: List<Profile> = uiState.value.profileList.profiles.minus(profileToRemove)
        _uiState.update {
            currentState ->
                currentState.copy(
                    profileList = uiState.value.profileList.copy(
                        profiles = newProfiles
                    )
                )
        }
        this.unsetRemoveProfile()
    }
}