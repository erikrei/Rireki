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
import com.example.rireki.data.dataclass.Profile
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
    onAddClick: (String, Profile) -> Unit,
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
                enabled =
                    profileValues.name.isNotEmpty() &&
                    profileValues.residency.isNotEmpty() &&
                    profileValues.age.isNotEmpty() &&
                    profileValues.description.isNotEmpty(),
                onAddClick = { onAddClick(homeUiState.selectedListId, Profile(
                    name = profileValues.name,
                    residency = profileValues.residency,
                    age = profileValues.age,
                    description = profileValues.description
                )) },
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
                LabelWithInput(
                    label = R.string.add_label_residence,
                    inputValue = profileValues.residency,
                    onValueChange = { addProfileViewModel.changeProfileResidence(it) }
                )
                LabelWithInput(
                    label = R.string.add_label_age,
                    inputValue = profileValues.age,
                    onValueChange = { addProfileViewModel.changeProfileAge(it) }
                )
                LabelWithInput(
                    label = R.string.add_label_description,
                    inputValue = profileValues.description,
                    onValueChange = { addProfileViewModel.changeProfileDescription(it) }
                )
            }
    }
}