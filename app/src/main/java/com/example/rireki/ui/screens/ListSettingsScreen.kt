package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.rireki.R
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.ui.components.ListSettingsAdminList
import com.example.rireki.ui.components.ListSettingsBottomSheet
import com.example.rireki.ui.components.ListSettingsTopBar
import com.example.rireki.ui.components.shared.ConfirmAlert
import com.example.rireki.ui.components.shared.LabelWithDropdown
import com.example.rireki.ui.components.shared.LabelWithIconButton
import com.example.rireki.ui.components.shared.LabelWithInput

@Composable
fun ListSettingsScreen(
    settingsViewModel: ListSettingsViewModel,
    onNavigateBack: () -> Unit,
    onListDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val settings by settingsViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ListSettingsTopBar(
            onNavigateBack = onNavigateBack
        ) }
    ) {
        paddingValues ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.settings_component_spacing)
                ),
                modifier = modifier
                    .padding(paddingValues)
                    .verticalScroll(state = rememberScrollState())
            ) {
                LabelWithInput(
                    label = R.string.settings_label_name,
                    inputValue = settings.listName,
                    onValueChange = { settingsViewModel.changeListName(it) },
                    modifier = Modifier
                )
                LabelWithDropdown(
                    label = R.string.settings_label_privacy,
                    expandedDropdown = settings.expandedDropdown,
                    activeDropdown = settings.listPrivacy,
                    onDissmissDropdown = { settingsViewModel.unshowDropdown() },
                    onDropdownClick = { settingsViewModel.setListPrivacy(it) },
                    dropdownItems = settings.listPrivacyOptions,
                    onDropdownOpen = { settingsViewModel.showDropdown() },
                    modifier = Modifier
                )
                LabelWithIconButton(
                    label = R.string.settings_label_admin,
                    icon = R.drawable.add_24,
                    onIconClick = { settingsViewModel.showAdminAddBottomSheet() },
                    modifier = Modifier
                )
                ListSettingsAdminList(
                    adminList = settings.admins,
                    showAdminDeleteDialog = { settingsViewModel.showAdminRemoveDialog(it) },
                    modifier = Modifier
                )
                LabelWithIconButton(
                    label = R.string.settings_delete_list_label,
                    icon = R.drawable.delete_24,
                    onIconClick = { settingsViewModel.showListDeleteDialog() },
                    iconColor = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                )
            }
        if (settings.expandedAdminRemove) {
            ConfirmAlert(
                onConfirmRequest = { settingsViewModel.deleteAdminFromList() },
                onDismissRequest = { settingsViewModel.unshowAdminRemoveDialog() },
                title = R.string.settings_admin_remove_title,
                confirmText = R.string.settings_admin_remove_confirm,
                dismissText = R.string.settings_admin_remove_dismiss,
                text = "Möchten Sie wirklich ${settings.adminDeleteSelected} als Admin entfernen?"
            )
        }
        if (settings.expandedListDelete) {
            ConfirmAlert(
                onConfirmRequest = { onListDelete(settings.listId) },
                onDismissRequest = { settingsViewModel.unshowListDeleteDialog() },
                title = R.string.settings_delete_list_title,
                confirmText = R.string.settings_delete_list_confirm,
                dismissText = R.string.settings_delete_list_dismiss,
                text = "Möchten Sie die Liste ${settings.listName} wirklich löschen?"
            )
        }
        if (settings.expandedAdminAddBottomSheet) {
            ListSettingsBottomSheet(
                value = settings.adminAdd,
                onDismissRequest = { settingsViewModel.unshowAdminAddBottomSheet() },
                onValueChange = { settingsViewModel.changeAdminAdd(it) },
                onAdminAdd = {  }, // @TODO
            )
        }
    }
}