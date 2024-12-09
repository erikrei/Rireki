package com.example.rireki.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.ui.components.shared.NavigationBackArrow
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ListOverviewTopBar(
    listName: String,
    onNavigateBack: () -> Unit,
    onNavigateSettings: () -> Unit,
    onNavigateAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { ListOverviewTitle(
            title = listName
        ) },
        navigationIcon = { NavigationBackArrow(
            onNavigateBack = onNavigateBack
        ) },
        actions = { ListOverviewActions(
            onNavigateSettings = onNavigateSettings,
            onNavigateAdd = onNavigateAdd
        ) },
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
fun ListOverviewActions(
    onNavigateSettings: () -> Unit,
    onNavigateAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        IconButton(
            onClick = onNavigateAdd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_person_24),
                contentDescription = null
            )
        }
        IconButton(
            onClick = onNavigateSettings
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
    profile: String,
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
                text = profile
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
fun ListOverviewNotFound(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.overview_dialog_not_found_text),
        modifier = modifier
    )
}