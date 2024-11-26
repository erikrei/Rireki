package com.example.rireki.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.ui.components.shared.LabelWithIconButton
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
fun ListSettingsAdminList(
    adminList: List<String>,
    showAdminDeleteDialog: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (adminList.isEmpty()) {
        Text(
            text = stringResource(id = R.string.settings_text_no_admin),
            modifier = modifier
                .fillMaxWidth()
        )
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.settings_admin_spacing)
            ),
            modifier = modifier
                .fillMaxWidth()
        ) {
            adminList.forEach {
                ListSettingsAdminRow(
                    admin = it,
                    showAdminDeleteDialog = showAdminDeleteDialog,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ListSettingsAdminRow(
    admin: String,
    showAdminDeleteDialog: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.settings_admin_border_width),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.settings_admin_border_size)
                )
            )
            .padding(
                all = dimensionResource(id = R.dimen.settings_admin_inner_padding)
            )
    ) {
        Text(
            text = admin
        )
        IconButton(
            onClick = { showAdminDeleteDialog(admin) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus_24),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListSettingsBottomSheet(
    value: String,
    onValueChange: (String) -> Unit,
    onAdminAdd: (String) -> Unit = {},
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.settings_add_admin_component_padding)
            ),
            modifier = Modifier
                .navigationBarsPadding()
                .padding(
                    dimensionResource(id = R.dimen.settings_add_admin_padding)
                )
        ) {
            var showInformation by remember {
                mutableStateOf(false)
            }

            LabelWithIconButton(
                label = R.string.settings_admin_add_label,
                icon = R.drawable.info_24,
                onIconClick = { showInformation = !showInformation }
            )
            AnimatedVisibility(
                visible = showInformation
            ) {
                Text(
                    text = stringResource(id = R.string.settings_admin_add_info_text)
                )
            }
            TextFieldWithIcon(
                textFieldValue = value,
                onValueChange = { onValueChange(it) },
                onAdminAdd = onAdminAdd
            )
        }
    }
}

@Composable
fun TextFieldWithIcon(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    onAdminAdd: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = onValueChange
        )
        IconButton(
            onClick = { onAdminAdd(textFieldValue) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }
}