package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.ProfileViewEditViewModel
import com.example.rireki.data.state.ProfileViewEditUiState
import com.example.rireki.ui.components.shared.LabelWithInput
import com.example.rireki.ui.components.shared.NavigationBackArrow
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ProfileViewEditContainer(
    profileEditValues: ProfileViewEditUiState,
    profileViewEditViewModel: ProfileViewEditViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.profile_edit_container_component_spacing)
        ),
        modifier = modifier
    ) {
        LabelWithInput(
            label = R.string.profile_edit_name_label,
            inputValue = profileEditValues.profileName,
            onValueChange = { profileViewEditViewModel.changeProfileName(it) }
        )
        LabelWithInput(
            label = R.string.profile_edit_residency_label,
            inputValue = profileEditValues.profileResidency,
            onValueChange = { profileViewEditViewModel.changeProfileResidency(it) }
        )
        LabelWithInput(
            label = R.string.profile_edit_age_label,
            inputValue = profileEditValues.profileAge,
            onValueChange = { profileViewEditViewModel.changeProfileAge(it) }
        )
        LabelWithInput(
            label = R.string.profile_edit_description_label,
            inputValue = profileEditValues.profileDescription,
            onValueChange = { profileViewEditViewModel.changeProfileDescription(it) }
        )
    }
}

@Composable
fun ProfileViewEditTopBar(
    profileName: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { ProfileViewEditTopBarTitle(profileName = profileName) }, 
        actions = { },
        navigationIcon = { NavigationBackArrow(onNavigateBack = onNavigateBack)},
        modifier = modifier
    )
}

@Composable
fun ProfileViewEditTopBarTitle(
    profileName: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.profile_edit_title, profileName),
        modifier = modifier
    )
}

@Composable
fun ProfileViewEditButton(
    enabled: Boolean = true,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onButtonClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.profile_edit_button_text)
        )
    }
}