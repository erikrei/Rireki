package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.state.HomeUiState
import com.example.rireki.ui.components.ListOverviewNotFound
import com.example.rireki.ui.components.ListOverviewProfile
import com.example.rireki.ui.components.ListOverviewTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert

@Composable
fun ListOverviewScreen(
    homeViewModel: HomeViewModel = viewModel(),
    homeUiState: HomeUiState,
    onNavigateBack: () -> Unit,
    onNavigateSettings: () -> Unit,
    onNavigateAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeProfileList = homeUiState.lists.find { it.id == homeUiState.selectedListId }

    Scaffold(
        topBar = { ListOverviewTopBar(
            listName = activeProfileList?.name ?: stringResource(id = R.string.overview_dialog_not_found_title),
            onNavigateBack = onNavigateBack,
            onNavigateSettings = onNavigateSettings,
            onNavigateAdd = onNavigateAdd
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
                            profile = profile,
                            onProfileRemoveClick = {
                                homeViewModel.setProfileToRemoveFromList(
                                    listId = activeProfileList.id,
                                    profileName = profile.name
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                if (activeProfileList.showRemoveProfile) {
                    ConfirmAlert(
                        onConfirmRequest = {
                            homeViewModel.removeProfileFromList(
                                listId = activeProfileList.id,
                                profileName = activeProfileList.removeProfile
                            )
                        },
                        onDismissRequest = { homeViewModel.unsetShowProfileRemove() },
                        title = R.string.overview_dialog_remove_title,
                        confirmText = R.string.overview_dialog_remove_confirm,
                        dismissText = R.string.overview_dialog_remove_dismiss,
                        text = "Möchten Sie wirklich ${activeProfileList.removeProfile} von der Liste entfernen?"
                    )
                }
            } else ListOverviewNotFound(
                modifier = overviewModifier
            )
    }
}