package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.rireki.R
import com.example.rireki.ui.components.shared.NavigationBackArrow
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ListAddTopBar(
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { ListAddTopBarTitle() },
        actions = { /*TODO*/ },
        navigationIcon = {
            NavigationBackArrow(
                onNavigateBack = onNavigationBack
            )
        },
        modifier = modifier
    )
}

@Composable
fun ListAddTopBarTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.add_title),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAddImage(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.add_image_component_spacing)
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    dimensionResource(id = R.dimen.add_image_padding)
                )
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.upload_24),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.add_upload_text)
            )
        }
    }
}

@Composable
fun ListAddButton(
    enabled: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onAddClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.add_button)
        )
    }
}