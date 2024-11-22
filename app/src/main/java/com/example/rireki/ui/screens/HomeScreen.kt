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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.util.getProfileList
import com.example.rireki.ui.components.HomeDialogAddList
import com.example.rireki.ui.components.HomeFloatingActionButton
import com.example.rireki.ui.components.HomeSingleList
import com.example.rireki.ui.components.HomeTopBar
import com.example.rireki.ui.theme.RirekiTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = { HomeFloatingActionButton(
            onClick = { homeViewModel.toggleIsOpenDialog() }
        ) },
        modifier = modifier
    ) {
        paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(homeUiState.lists) {
                    HomeSingleList(
                        profileList = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.profile_list_outer_horizontal_padding),
                                end = dimensionResource(id = R.dimen.profile_list_outer_horizontal_padding),
                                top = dimensionResource(id = R.dimen.profile_list_outer_vertical_padding)
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
            if (homeUiState.isOpenDialog) {
                HomeDialogAddList(
                    listName = homeUiState.newListName,
                    onListNameChange = { homeViewModel.updateNameInput(it) },
                    onSubmit = {
                        homeViewModel.addList(
                            getProfileList(homeUiState.newListName)
                        )
                    },
                    onDismissRequest = { homeViewModel.toggleIsOpenDialog() }
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