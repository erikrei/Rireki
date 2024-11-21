package com.example.rireki.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.data.dummyLists
import com.example.rireki.ui.components.HomeDialogAddList
import com.example.rireki.ui.components.HomeFloatingActionButton
import com.example.rireki.ui.components.HomeSingleList
import com.example.rireki.ui.components.HomeTopBar
import com.example.rireki.ui.theme.RirekiTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = { HomeFloatingActionButton(
            onClick = { openDialog = true }
        ) },
        modifier = modifier
    ) {
        paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(dummyLists) {
                    HomeSingleList(
                        profileList = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = dimensionResource(id = R.dimen.profile_list_outer_vertical_padding),
                                horizontal = dimensionResource(id = R.dimen.profile_list_outer_horizontal_padding)
                            )
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(
                                    dimensionResource(id = R.dimen.profile_list_border_shape_size)
                                )
                            )
                    )
                }
            }
            if (openDialog) {
                HomeDialogAddList(
                    onSubmit = { /* @TODO Wenn Liste hinzugefügt werden soll */ },
                    onDismissRequest = { openDialog = false }
                )
            }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    RirekiTheme {
        HomeScreen()
    }
}