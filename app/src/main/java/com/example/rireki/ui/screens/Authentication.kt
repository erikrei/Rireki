package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.ui.components.AuthFooter
import com.example.rireki.ui.components.AuthHeader
import com.example.rireki.ui.components.AuthSubmitButton
import com.example.rireki.ui.components.AuthTextField
import com.example.rireki.ui.theme.RirekiTheme

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier
) {
    var isLogin by remember {
        mutableStateOf(true)
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val toggleIsLogin: () -> Unit = {
        isLogin = !isLogin
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(id = R.dimen.auth_horizontal_padding),
                vertical = dimensionResource(id = R.dimen.auth_vertical_padding)
            )
    ) {
        AuthHeader(
            isLogin = isLogin
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AuthTextField(
                value = email,
                onValueChange = { email = it},
                label = R.string.email_label,
                fieldIcon = R.drawable.mail_24,
            )
            AuthTextField(
                value = password,
                onValueChange = { password = it},
                label = R.string.password_label,
                fieldIcon = R.drawable.key_24
            )
            AuthSubmitButton(
                isLogin = isLogin,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        AuthFooter(
            isLogin = isLogin,
            onAuthSwitch = toggleIsLogin
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun AuthenticationScreenPreview() {
    RirekiTheme {
        AuthenticationScreen()
    }
}

