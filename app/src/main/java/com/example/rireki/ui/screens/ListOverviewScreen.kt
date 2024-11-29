package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.model.ActiveProfileListViewModel
import com.example.rireki.ui.components.ListOverviewProfile
import com.example.rireki.ui.components.ListOverviewTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert

@Composable
fun ListOverviewScreen(
    activeProfileListViewModel: ActiveProfileListViewModel = viewModel(),
    selectedList: ProfileList,
    onNavigateBack: () -> Unit,
    onNavigateSettings: () -> Unit,
    onNavigateAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeProfileListUiState by activeProfileListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ListOverviewTopBar(
            listName = selectedList.name,
            onNavigateBack = onNavigateBack,
            onNavigateSettings = onNavigateSettings,
            onNavigateAdd = onNavigateAdd
        ) },
        modifier = modifier
    ) {
        paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                itemsIndexed(activeProfileListUiState.profileList.profiles) {
                    index, profile ->
                        val isLastElement = index == selectedList.profiles.size - 1
                        ListOverviewProfile(
                            profile = profile,
                            onProfileRemoveClick = {
                                activeProfileListViewModel.setRemoveProfile(profile = profile)
                            },
                            modifier = Modifier
                                .padding(
                                    start = dimensionResource(id = R.dimen.simple_list_outer_horizontal_padding),
                                    end = dimensionResource(id = R.dimen.simple_list_outer_horizontal_padding),
                                    top = dimensionResource(id = R.dimen.simple_list_outer_vertical_padding),
                                    bottom = if (isLastElement)
                                        dimensionResource(id = R.dimen.simple_list_outer_vertical_padding)
                                    else 0.dp
                                )
                                .fillMaxWidth()
                        )
                }
            }
            if (activeProfileListUiState.showRemoveProfile) {
                ConfirmAlert(
                    onConfirmRequest = { activeProfileListViewModel.removeProfile() },
                    onDismissRequest = { activeProfileListViewModel.unsetRemoveProfile() },
                    title = R.string.overview_dialog_remove_title,
                    confirmText = R.string.overview_dialog_remove_confirm,
                    dismissText = R.string.overview_dialog_remove_dismiss,
                    text = "Möchten Sie wirklich ${activeProfileListUiState.removeProfile.name} von der Liste entfernen?"
                )
            }
    }
}