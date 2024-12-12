package com.example.rireki.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.rireki.R
import com.example.rireki.data.dataclass.Admin
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
fun ListSettingsMemberOverview(
    member: List<String>,
    admins: List<Admin>,
    onUserAddClick: () -> Unit,
    onAdminAddClick: (String) -> Unit,
    onAdminRemoveClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showMember by remember { mutableStateOf(false) }

    val toggleShowMember: () -> Unit = { showMember = !showMember }

    ListSettingsCategory(
        category = stringResource(id = R.string.settings_category_member),
        actions = {
            ListSettingsMemberOverviewActions(
                showMember = showMember,
                onToggleShowMember = toggleShowMember,
                onShowUserAdd = onUserAddClick
            )
        },
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = showMember
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.settings_member_component_spacing)
                )
            ) {
                member.forEach {
                    member ->
                        ListSettingsMember(
                            member = member,
                            isAdmin = admins.find { it.userId == member } != null,
                            onAdminAddClick = { onAdminAddClick(member) },
                            onAdminRemoveClick = { onAdminRemoveClick(member) },
                            modifier = Modifier.fillMaxWidth()
                        )
                }
            }
        }
    }
}

@Composable
fun ListSettingsMemberOverviewActions(
    showMember: Boolean,
    onToggleShowMember: () -> Unit,
    onShowUserAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = onShowUserAdd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = null
            )
        }
        IconButton(
            onClick = onToggleShowMember
        ) {
            if (showMember) Icon(painter = painterResource(id = R.drawable.arrow_drop_up_24), contentDescription = null)
            else Icon(painter = painterResource(id = R.drawable.arrow_drop_down_24), contentDescription = null)
        }
    }
}

@Composable
fun ListSettingsMember(
    member: String,
    isAdmin: Boolean,
    onAdminAddClick: () -> Unit = {},
    onAdminRemoveClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.settings_member_component_spacing))
        ) {
            Text(text = member)
            IconButton(
                onClick = if (isAdmin) onAdminRemoveClick else onAdminAddClick
            ) {
                Icon(
                    painter = if (isAdmin) painterResource(id = R.drawable.remove_admin_24)
                        else painterResource(id = R.drawable.add_admin_24),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ListSettingsMemberAddDialog(
    userInput: String,
    dialogText: String,
    onUserInputChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dialog_card_inner_spacing)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dialog_card_inner_padding))
            ) {
                Text(
                    text = stringResource(id = R.string.settings_user_add_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(text = dialogText)
                LabelWithInput(
                    label = R.string.settings_user_add_input_label,
                    inputValue = userInput,
                    onValueChange = onUserInputChange
                )
                ListSettingsMemberAddDialogButtons(
                    onConfirm = onConfirmRequest,
                    onDismiss = onDismissRequest,
                    confirmEnabled = userInput.isNotEmpty(),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun ListSettingsMemberAddDialogButtons(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TextButton(
            onClick = onDismiss
        ) {
            Text(text = stringResource(id = R.string.settings_delete_dismiss).uppercase())
        }
        TextButton(
            onClick = onConfirm,
            enabled = confirmEnabled
        ) {
            Text(text = stringResource(id = R.string.settings_user_add_dialog_confirm).uppercase())
        }
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