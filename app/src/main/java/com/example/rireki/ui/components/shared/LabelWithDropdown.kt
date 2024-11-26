package com.example.rireki.ui.components.shared

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rireki.R

@Composable
fun LabelWithDropdown(
    expandedDropdown: Boolean,
    activeDropdown: String,
    dropdownItems: List<String>,
    onDissmissDropdown: () -> Unit,
    onDropdownClick: (String) -> Unit,
    onDropdownOpen: () -> Unit,
    @StringRes label: Int,
    labelColor: Color? = null,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = label),
            color = labelColor ?: Color.Unspecified
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable {
                    onDropdownOpen()
                }
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.settings_privacy_spacing)
                    )
            ) {
                Text(
                    text = activeDropdown,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.settings_privacy_spacing)
                        )
                )
                Icon(
                    painter =
                    if (expandedDropdown)
                        painterResource(id = R.drawable.arrow_drop_up_24)
                    else painterResource(id = R.drawable.arrow_drop_down_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
    DropdownMenu(
        expanded = expandedDropdown,
        onDismissRequest = onDissmissDropdown,
    ) {
        dropdownItems.forEach {
                item ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = item
                    )
                },
                onClick = {
                    onDropdownClick(item)
                },
            )
        }
    }
}