package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.util.getProfileList
import com.example.rireki.ui.components.HomeDialogAddList
import com.example.rireki.ui.components.HomeFloatingActionButton
import com.example.rireki.ui.components.HomeSingleList
import com.example.rireki.ui.components.HomeSnackbar
import com.example.rireki.ui.components.HomeTopBar
import com.example.rireki.ui.theme.RirekiTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navigateToList: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val listCreatedMessage = stringResource(id = R.string.dialog_snackbar_created, homeUiState.newListName)

    val showListCreatedSnackbar: () -> Unit = {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = listCreatedMessage,
                withDismissAction = true,
            )
        }
    }
    
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = {
            HomeFloatingActionButton(
                onClick = { homeViewModel.toggleIsOpenDialog() }
            )
        },
        snackbarHost = {
           HomeSnackbar(
               snackbarHostState = snackbarHostState
           )
        },
        modifier = modifier
    ) {
        paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(homeUiState.lists) {
                    HomeSingleList(
                        onListClick = { navigateToList(it.id) },
                        profileList = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.profile_list_outer_horizontal_padding),
                                end = dimensionResource(id = R.dimen.profile_list_outer_horizontal_padding),
                                top = dimensionResource(id = R.dimen.profile_list_outer_vertical_padding)
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
                        ) { showListCreatedSnackbar() }
                    },
                    onDismissRequest = { homeViewModel.toggleIsOpenDialog() },
                    error = homeUiState.error,
                )
            }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    RirekiTheme {
        HomeScreen(navigateToList = {})
    }
}