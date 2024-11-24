package com.example.rireki.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.data.Profile
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ListOverviewTopBar(
    listName: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { ListOverviewTitle(
            title = listName
        ) },
        navigationIcon = { ListOverviewNavigationBack(
            onNavigateBack = onNavigateBack
        ) },
        actions = { ListOverviewActions() },
        modifier = modifier
    )
}

@Composable
fun ListOverviewTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier
    )
}

@Composable
fun ListOverviewNavigationBack(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onNavigateBack,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_24),
            contentDescription = null
        )
    }
}

@Composable
fun ListOverviewActions(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_person_24),
                contentDescription = null
            )
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings_24),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOverviewProfile(
    onProfileRemoveClick: () -> Unit,
    profile: Profile,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.simple_list_inner_padding)
                )
        ) {
            ListOverviewProfileImage(
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.simple_list_inner_component_padding)
                    )
            )
            Text(
                text = profile.name 
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onProfileRemoveClick,
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.remove_person_24),
                    contentDescription = null,
                )    
            }
        }
    }
}

@Composable
fun ListOverviewProfileImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = modifier
            .size(40.dp)
            .clip(shape = CircleShape),
    )
}

@Composable
fun ListOverviewRemoveProfileAlert(
    profileToRemove: String,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(id = R.string.overview_dialog_remove_title)
            )
        },
        text = {
            Text(
                text = "Möchten Sie wirklich $profileToRemove von der Liste entfernen?"
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmRequest
            ) {
                Text(
                    text = stringResource(id = R.string.overview_dialog_remove_confirm).uppercase(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(id = R.string.overview_dialog_remove_dismiss).uppercase()
                )
            }
        },
        modifier = modifier
    )
}