package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.AddProfileViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.ui.components.ListAddButton
import com.example.rireki.ui.components.ListAddImage
import com.example.rireki.ui.components.ListAddTopBar
import com.example.rireki.ui.components.shared.LabelWithInput

@Composable
fun ListAddScreen(
    addProfileViewModel: AddProfileViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    onNavigationBack: () -> Unit,
    onAddClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val profileValues by addProfileViewModel.uiState.collectAsState()
    val homeUiState by homeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ListAddTopBar(
                onNavigationBack = onNavigationBack
            )
        },
        bottomBar = {
            ListAddButton(
//                enabled = profileValues.name.isNotEmpty() && profileValues.residence.isNotEmpty(),
                enabled = profileValues.name.isNotEmpty(),
                onAddClick = { onAddClick(homeUiState.selectedListId, profileValues.name) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.add_bottom_bar_padding),
                        end = dimensionResource(id = R.dimen.add_bottom_bar_padding),
                        bottom = dimensionResource(id = R.dimen.add_bottom_bar_padding),
                    )
            )
        },
        modifier = modifier
    ) {
        paddingValues ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.add_component_spacing)
                ),
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(
                        vertical = dimensionResource(id = R.dimen.add_vertical_padding),
                        horizontal = dimensionResource(id = R.dimen.add_horizontal_padding)
                    )
            ) {
                ListAddImage(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                LabelWithInput(
                    label = R.string.add_label_name,
                    inputValue = profileValues.name,
                    onValueChange = { addProfileViewModel.changeProfileName(it) }
                )
//                LabelWithInput(
//                    label = R.string.add_label_residence,
//                    inputValue = profileValues.residence,
//                    onValueChange = { addProfileViewModel.changeProfileResidence(it) }
//                )
            }
    }
}