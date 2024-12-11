package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.state.ProfileViewEditUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewEditViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileViewEditUiState())
    val uiState: StateFlow<ProfileViewEditUiState> = _uiState.asStateFlow()

    fun setProfileToEdit(profile: Profile?) {
        if (profile != null)
            _uiState.update {
                    currentState ->
                currentState.copy(
                    profileName = profile.name,
                    profileResidency = profile.residency,
                    profileAge = profile.age,
                    profileDescription = profile.description
                )
            }
    }

    fun getProfileOfState(): Profile {
        return Profile(
            name = uiState.value.profileName,
            residency = uiState.value.profileResidency,
            age = uiState.value.profileAge,
            description = uiState.value.profileDescription,
        )
    }

    fun changeProfileName(profileNameInput: String) {
        _uiState.update { it.copy(profileName = profileNameInput) }
    }

    fun changeProfileResidency(profileResidencyInput: String) {
        _uiState.update { it.copy(profileResidency = profileResidencyInput) }
    }

    fun changeProfileAge(profileAgeInput: String) {
        _uiState.update { it.copy(profileAge = profileAgeInput) }
    }

    fun changeProfileDescription(profileDescriptionInput: String) {
        _uiState.update { it.copy(profileDescription = profileDescriptionInput) }
    }
}