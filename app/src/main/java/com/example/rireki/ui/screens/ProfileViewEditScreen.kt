package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.model.ProfileViewEditViewModel
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.data.objects.ProfileViewEdit
import com.example.rireki.ui.components.ProfileViewEditButton
import com.example.rireki.ui.components.ProfileViewEditContainer
import com.example.rireki.ui.components.ProfileViewEditTopBar

@Composable
fun ProfileViewEditScreen(
    userViewModel: UserViewModel = viewModel(),
    profileViewEditViewModel: ProfileViewEditViewModel = viewModel(),
    profileViewEdit: ProfileViewEdit,
    onNavigateBack: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val profileViewEditUiState by profileViewEditViewModel.uiState.collectAsState()

    val profile = userViewModel.getProfileFromListIdAndName(
        listId = profileViewEdit.listId,
        profileName = profileViewEdit.name
    ) ?: Profile()

    LaunchedEffect(key1 = profileViewEdit) {
        profileViewEditViewModel.setProfileToEdit(profile)
    }

    Scaffold(
        topBar = { ProfileViewEditTopBar(
            profileName = profileViewEdit.name,
            onNavigateBack = { onNavigateBack(profileViewEdit.listId, profileViewEdit.name) }
        ) },
        bottomBar = { ProfileViewEditButton(
            onButtonClick = { userViewModel.editUser(
                listId = profileViewEdit.listId,
                profileName = profileViewEdit.name,
                newProfile = profileViewEditViewModel.getProfileOfState()
            ) { onNavigateBack(profileViewEdit.listId, profileViewEditUiState.profileName) } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.profile_edit_button_padding),
                    end = dimensionResource(id = R.dimen.profile_edit_button_padding),
                    bottom = dimensionResource(id = R.dimen.profile_edit_button_padding),
                )
        ) },
        modifier = modifier
    ) {
        paddingValues ->
            ProfileViewEditContainer(
                profileEditValues = profileViewEditUiState,
                profileViewEditViewModel = profileViewEditViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.profile_edit_container_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.profile_edit_container_vertical_padding)
                    )
                    .fillMaxSize()
            )
    }
}