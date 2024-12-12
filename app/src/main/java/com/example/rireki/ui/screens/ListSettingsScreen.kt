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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.ui.components.ListSettingsDanger
import com.example.rireki.ui.components.ListSettingsGeneral
import com.example.rireki.ui.components.ListSettingsMemberAddDialog
import com.example.rireki.ui.components.ListSettingsMemberOverview
import com.example.rireki.ui.components.ListSettingsPrivacy
import com.example.rireki.ui.components.ListSettingsPrivacyDialog
import com.example.rireki.ui.components.ListSettingsTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert

@Composable
fun ListSettingsScreen(
    settingsViewModel: ListSettingsViewModel = viewModel(),
    selectedList: ProfileList,
    onAdminOperation: (String, String, () -> Unit) -> Unit,
    onUserAdd: (String, () -> Unit) -> Unit,
    onNavigateBack: () -> Unit,
    onListDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val settings by settingsViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ListSettingsTopBar(
            onNavigateBack = onNavigateBack
        ) },
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
                ListSettingsMemberOverview(
                    member = selectedList.follower,
                    admins = selectedList.settings.admins,
                    onUserAddClick = { settingsViewModel.showUserAddDialog() },
                    onAdminAddClick = {
                        adminToAdd ->
                            settingsViewModel.showAdminAddDialog(adminToAdd = adminToAdd)
                    },
                    onAdminRemoveClick = {
                        adminToRemove ->
                            settingsViewModel.showAdminRemoveDialog(adminToRemove = adminToRemove)
                    }
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
                dismissText = R.string.settings_delete_dismiss,
                text = "Möchten Sie die Liste ${settings.newName} wirklich löschen?"
            )
        }
        if (settings.expandedAdminAdd) {
            ConfirmAlert(
                onConfirmRequest = { onAdminOperation(
                    settings.dialogAdmin,
                    "add"
                ) { settingsViewModel.unshowAdminAddDialog() } },
                onDismissRequest = { settingsViewModel.unshowAdminAddDialog() },
                title = R.string.settings_admin_add_title,
                confirmText = R.string.settings_admin_add_confirm,
                dismissText = R.string.settings_delete_dismiss,
                text = "Möchten Sie ${settings.dialogAdmin} als Admin hinzufügen?"
            )
        }
        if (settings.expandedAdminRemove) {
            ConfirmAlert(
                onConfirmRequest = { onAdminOperation(
                    settings.dialogAdmin,
                    "remove"
                ) { settingsViewModel.unshowAdminRemoveDialog() }},
                onDismissRequest = { settingsViewModel.unshowAdminRemoveDialog() },
                title = R.string.settings_admin_remove_title,
                confirmText = R.string.settings_admin_remove_confirm,
                dismissText = R.string.settings_delete_dismiss,
                text = "Möchten Sie ${settings.dialogAdmin} als Admin entfernen?"
            )
        }
        if (settings.expandedUserAdd) {
            ListSettingsMemberAddDialog(
                userInput = settings.userAdd,
                dialogText = stringResource(id = R.string.settings_user_add_dialog_text),
                onUserInputChange = { settingsViewModel.changeUserAddInput(it) },
                onConfirmRequest = { onUserAdd(settings.userAdd) { settingsViewModel.unshowUserAddDialog()} },
                onDismissRequest = { settingsViewModel.unshowUserAddDialog() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}