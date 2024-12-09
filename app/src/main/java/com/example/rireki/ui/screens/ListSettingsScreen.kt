package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.data.util.checkIfSettingsEqual
import com.example.rireki.ui.components.ListSettingsDanger
import com.example.rireki.ui.components.ListSettingsGeneral
import com.example.rireki.ui.components.ListSettingsPrivacy
import com.example.rireki.ui.components.ListSettingsPrivacyDialog
import com.example.rireki.ui.components.ListSettingsSaveButton
import com.example.rireki.ui.components.ListSettingsTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert

@Composable
fun ListSettingsScreen(
    settingsViewModel: ListSettingsViewModel = viewModel(),
    selectedList: ProfileList,
    onNavigateBack: () -> Unit,
    onListDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val settings by settingsViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ListSettingsTopBar(
            onNavigateBack = onNavigateBack
        ) },
        bottomBar = {
            ListSettingsSaveButton(
                onClick = { /*TODO*/ },
                enabled = !checkIfSettingsEqual(selectedList.name, selectedList.settings, settings),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.settings_bottom_bar_padding),
                        end = dimensionResource(id = R.dimen.settings_bottom_bar_padding),
                        bottom = dimensionResource(id = R.dimen.settings_bottom_bar_padding)
                    )
            )
        },
        modifier = modifier
    ) {
        paddingValues ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.settings_all_component_spacing_new)
                ),
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.settings_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.settings_vertical_padding)
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                ListSettingsGeneral(
                    category = R.string.settings_category_general,
                    inputValue = settings.newName,
                    onValueChange = { settingsViewModel.changeListName(it) }
                )
                ListSettingsPrivacy(
                    activePrivacy = settings.privacy,
                    showDropdown = settings.expandedDropdown,
                    onDropdownOpen = { settingsViewModel.showPrivacyDialog() }
                )
                ListSettingsDanger(
                    onShowListDeleteDialog = { settingsViewModel.showListDeleteDialog() },
                )
            }
        if (settings.expandedDropdown) {
            ListSettingsPrivacyDialog(
                onPrivacySelect = { settingsViewModel.setListPrivacy(it) },
                onDismissRequest = { settingsViewModel.unshowPrivacyDialog() },
                activePrivacy = settings.privacy,
                privacyOptions = settings.privacyOptions
            )
        }
        if (settings.expandedListDelete) {
            ConfirmAlert(
                onConfirmRequest = { onListDelete(selectedList.id) },
                onDismissRequest = { settingsViewModel.unshowListDeleteDialog() },
                title = R.string.settings_delete_list_title,
                confirmText = R.string.settings_delete_list_confirm,
                dismissText = R.string.settings_delete_list_dismiss,
                text = "Möchten Sie die Liste ${settings.newName} wirklich löschen?"
            )
        }
    }
}