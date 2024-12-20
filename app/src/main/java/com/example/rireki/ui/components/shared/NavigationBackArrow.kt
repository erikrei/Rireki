package com.example.rireki.ui.components.shared

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.rireki.R

@Composable
fun NavigationBackArrow(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onNavigateBack,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_24),
            contentDescription = null
        )
    }
}