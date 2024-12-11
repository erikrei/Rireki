package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ProfileDeleteViewModel
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.ui.components.ListOverviewNotFound
import com.example.rireki.ui.components.ListOverviewProfile
import com.example.rireki.ui.components.ListOverviewTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert

@Composable
fun ListOverviewScreen(
    homeViewModel: HomeViewModel = viewModel(),
    profileDeleteViewModel: ProfileDeleteViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    onProfileClick: (String, String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateSettings: () -> Unit,
    onNavigateAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userUiState by userViewModel.uiState.collectAsState()
    val profileDeleteUiState by profileDeleteViewModel.uiState.collectAsState()
    val homeUiState by homeViewModel.uiState.collectAsState()

    val activeProfileList = userUiState.userData.find { it.id == homeUiState.selectedListId }

    val isUserAdmin = userViewModel.isUserAdmin(homeUiState.selectedListId)

    Scaffold(
        topBar = { ListOverviewTopBar(
            isAdmin = isUserAdmin,
            listName = activeProfileList?.name ?: stringResource(id = R.string.overview_dialog_not_found_title),
            onNavigateBack = onNavigateBack,
            onNavigateSettings = onNavigateSettings,
            onNavigateAdd = onNavigateAdd,
            onLeaveListClick = { profileDeleteViewModel.toggleUserLeave() }
        ) },
        modifier = modifier
    ) {
        paddingValues ->
            val overviewModifier =
                Modifier
                    .padding(paddingValues)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.simple_list_container_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.simple_list_container_vertical_padding)
                    )
            if (activeProfileList != null) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.simple_list_elements_spacing)
                    ),
                    modifier = overviewModifier
                ) {
                    items(activeProfileList.profiles) {
                        profile ->
                            ListOverviewProfile(
                                isAdmin = isUserAdmin,
                                onProfileClick = {
                                    onProfileClick(
                                        homeUiState.selectedListId,
                                        profile.name
                                    )
                                },
                                profile = profile.name,
                                onProfileRemoveClick = {
                                    profileDeleteViewModel.setProfileToRemoveFromList(
                                        listId = activeProfileList.id,
                                        profileName = profile.name
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                    }
                }
                if (profileDeleteUiState.showRemoveProfile) {
                    ConfirmAlert(
                        onConfirmRequest = {
                            userViewModel.removeProfileFromList(
                                listId = activeProfileList.id,
                                profile = Profile(name = profileDeleteUiState.removeProfile)
                            ) {
                                profileDeleteViewModel.unsetShowProfileRemove()
                            }
                        },
                        onDismissRequest = { profileDeleteViewModel.unsetShowProfileRemove() },
                        title = R.string.overview_dialog_remove_title,
                        confirmText = R.string.overview_dialog_remove_confirm,
                        dismissText = R.string.overview_dialog_remove_dismiss,
                        text = "Möchten Sie wirklich ${profileDeleteUiState.removeProfile} von der Liste entfernen?"
                    )
                }
                if (profileDeleteUiState.userLeave) {
                    ConfirmAlert(
                        onConfirmRequest = {
                            profileDeleteViewModel.toggleUserLeave()
                            userViewModel.leaveList(homeUiState.selectedListId) {
                                onNavigateBack()
                            }
                        },
                        onDismissRequest = { profileDeleteViewModel.toggleUserLeave() },
                        title = R.string.overview_dialog_user_leave_title,
                        confirmText = R.string.overview_dialog_user_leave_confirm,
                        dismissText = R.string.overview_dialog_user_leave_dismiss,
                        text = stringResource(id = R.string.overview_dialog_user_leave_text)
                    )
                }
            } else ListOverviewNotFound(
                modifier = overviewModifier
            )
    }
}