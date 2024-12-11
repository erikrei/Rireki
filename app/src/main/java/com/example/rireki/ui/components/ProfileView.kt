package com.example.rireki.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.data.dataclass.Profile
import com.example.rireki.ui.components.shared.LabelWithText
import com.example.rireki.ui.components.shared.NavigationBackArrow
import com.example.rireki.ui.components.shared.TopBar

@Composable
fun ProfileViewTopBar(
    profileName: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        title = { ProfileViewTopBarTitle(title = profileName) },
        actions = { /*TODO*/ },
        navigationIcon = { NavigationBackArrow(onNavigateBack = onNavigateBack) },
        modifier = modifier
    )
}

@Composable
fun ProfileViewTopBarTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier
    )
}

@Composable
fun ProfileViewContainer(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.profile_view_container_spacing)
        ),
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.profile_view_container_horizontal_padding),
                vertical = dimensionResource(id = R.dimen.profile_view_container_vertical_padding)
            )
            .fillMaxSize()
    ) {
        ProfileViewAgeResidency(
            profileAge = profile.age,
            profileResidency = profile.residency,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Divider(
            thickness = 1.dp
        )
        ProfileViewDescription(
            description = profile.description
        )
    }
}

@Composable
fun ProfileViewDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    LabelWithText(
        labelText = stringResource(id = R.string.profile_view_description_label),
        contentText = description,
        spacing = dimensionResource(id = R.dimen.profile_view_prop_container_spacing),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun ProfileViewAgeResidency(
    profileAge: String,
    profileResidency: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.profile_view_age_residency_spacing)
        ),
        modifier = modifier
    ) {
        ProfileViewPropContainer(
            icon = R.drawable.badge_24, 
            propName = "$profileAge Jahre"
        )
        ProfileViewPropContainer(
            icon = R.drawable.residency_24, 
            propName = profileResidency
        )
    }
}

@Composable
fun ProfileViewPropContainer(
    @DrawableRes icon: Int,
    propName: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.profile_view_prop_container_spacing)
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon), 
            contentDescription = null
        )
        Text(
            text = propName,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileViewNotFound(
    modifier: Modifier = Modifier
) {
    Text(
        text = "PROFILE NOT FOUND",
        modifier = modifier
    )
}