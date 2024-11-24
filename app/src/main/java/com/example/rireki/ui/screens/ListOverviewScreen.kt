package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.data.ProfileList
import com.example.rireki.ui.components.ListOverviewProfile
import com.example.rireki.ui.components.ListOverviewTopBar

@Composable
fun ListOverviewScreen(
    selectedList: ProfileList,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { ListOverviewTopBar(
            listName = selectedList.name,
            onNavigateBack = onNavigateBack
        ) }
    ) {
        paddingValues ->
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
            ) {
                itemsIndexed(selectedList.profiles) {
                    index, profile ->
                        val isLastElement = index == selectedList.profiles.size - 1
                        ListOverviewProfile(
                            profile = profile,
                            modifier = Modifier
                                .padding(
                                    start = dimensionResource(id = R.dimen.simple_list_outer_horizontal_padding),
                                    end = dimensionResource(id = R.dimen.simple_list_outer_horizontal_padding),
                                    top = dimensionResource(id = R.dimen.simple_list_outer_vertical_padding),
                                    bottom = if (isLastElement)
                                                dimensionResource(id = R.dimen.simple_list_outer_vertical_padding)
                                             else 0.dp
                                )
                                .fillMaxWidth()
                        )
                }
            }
    }
}