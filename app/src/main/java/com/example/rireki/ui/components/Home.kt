package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.rireki.R
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { HomeTopBarTitle() },
        actions = { HomeTopBarActions() },
        modifier = modifier
    )
}

@Composable
fun HomeTopBarTitle(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.topbar_title_space)
        ),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.topbar_title_icon_size)
                )
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
fun HomeTopBarActions(
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.notification_24),
            contentDescription = null,
        )
    }
}

@Composable
fun HomeSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    ) {
        Snackbar(
            snackbarData = it,
        )
    }
}

@Composable
fun HomeShareUserId(
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onShareClick,
        shape = RectangleShape,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_share_user_id),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        text = { 
               Text(text = "Liste hinzufügen")
        },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = null
            )
        },
        onClick = onClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSingleList(
    onListClick: () -> Unit,
    profileList: ProfileList,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onListClick,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.profile_list_inner_padding)
                    )
            ) {
                Text(
                    text = profileList.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "erstellt von: ${profileList.createdFrom}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Text(
                text = profileList.createdAt,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.profile_list_created_padding)
                    )
            )
        }
    }
}