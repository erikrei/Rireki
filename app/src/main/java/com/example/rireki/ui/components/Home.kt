package com.example.rireki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.rireki.R
import com.example.rireki.data.objects.ProfileList
import com.example.rireki.data.util.getMonthName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.topbar_title_space)
                )
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
        },
        actions = {
              IconButton(
                  onClick = { /*TODO*/ }
              ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_24),
                    contentDescription = null,
                )    
              }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@Composable
fun HomeFloatingActionButton(
    modifier: Modifier = Modifier
) {
//    FloatingActionButton(
//        onClick = { /*TODO*/ },
//        modifier = modifier
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.add_24),
//            contentDescription = null
//        )
//    }
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
        onClick = { /*TODO*/ },
        modifier = modifier
    )
}

@Composable
fun HomeSingleList(
    profileList: ProfileList,
    modifier: Modifier = Modifier
) {
    val day = profileList.createdAt.dayOfMonth
    val monthName = getMonthName(profileList.createdAt)
    val year = profileList.createdAt.year

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
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
            text = "$day. $monthName $year",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(
                    dimensionResource(id = R.dimen.profile_list_created_padding)
                )
        )
    }
}