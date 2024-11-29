package com.example.rireki.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.rireki.R
import com.example.rireki.ui.components.shared.LabelWithDropdown
import com.example.rireki.ui.components.shared.LabelWithIconButton
import com.example.rireki.ui.components.shared.LabelWithInput
import com.example.rireki.ui.components.shared.NavigationBackArrow
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ListSettingsTopBar(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        navigationIcon = {
             NavigationBackArrow(
                 onNavigateBack = onNavigateBack
             )
        },
        title = { ListSettingsTopBarTitle() },
        actions = { /*TODO*/ },
        modifier = modifier
    )
}

@Composable
fun ListSettingsTopBarTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.settings_title),
        modifier = modifier
    )
}

@Composable
fun ListSettingsCategory(
    category: String,
    actions: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier,
    categoryContent: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.settings_single_component_spacing_new)
        ),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.titleLarge
            )
            if (actions != null) actions()
        }
        Divider(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            thickness = dimensionResource(id = R.dimen.settings_category_divider_width)
        )
        categoryContent()
    }
}

@Composable
fun ListSettingsGeneral(
    inputValue: String,
    onValueChange: (String) -> Unit,
    @StringRes category: Int,
    modifier: Modifier = Modifier
) {
    ListSettingsCategory(
        category = stringResource(id = category),
        modifier = modifier
    ) {
        LabelWithInput(
            label = R.string.settings_label_name,
            inputValue = inputValue,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ListSettingsPrivacy(
    activePrivacy: String,
    showDropdown: Boolean,
    onDropdownOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListSettingsCategory(
        category = stringResource(id = R.string.settings_category_privacy),
        modifier = modifier
    ) {
        LabelWithDropdown(
            label = R.string.settings_label_privacy,
            expandedDropdown = showDropdown,
            activeDropdown = activePrivacy,
            onDropdownOpen = onDropdownOpen,
            modifier = Modifier
        )
    }
}

@Composable
fun ListSettingsDanger(
    onShowListDeleteDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListSettingsCategory(
        category = stringResource(id = R.string.settings_category_danger),
        modifier = modifier
    ) {
        LabelWithIconButton(
            label = R.string.settings_delete_list_label,
            icon = R.drawable.delete_24,
            onIconClick = onShowListDeleteDialog,
            iconColor = MaterialTheme.colorScheme.error,
        )
    }
}

@Composable
fun ListSettingsPrivacyDialog(
    activePrivacy: String,
    privacyOptions: List<String>,
    onPrivacySelect: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val eligableOptions = privacyOptions.minus(activePrivacy)

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    )

            ) {
                eligableOptions.forEachIndexed {
                    index, option ->
                        ListSettingsPrivacyOption(
                            option = option,
                            onPrivacySelect = onPrivacySelect
                        )
                        if (index != eligableOptions.size - 1) {
                            Divider(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                }
            }
        }
    }
}

@Composable
fun ListSettingsPrivacyOption(
    option: String,
    onPrivacySelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onPrivacySelect(option) }
            .padding(
                dimensionResource(id = R.dimen.settings_privacy_option_padding)
            )
    ) {
        Text(
            text = option,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun ListSettingsSaveButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.settings_save_button)
        )
    }
}