package com.example.rireki.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.data.enumclass.AUTH_ERROR
import com.example.rireki.data.enumclass.TOAST_TYPE

@Composable
fun AuthHeader(
    isLogin: Boolean,
    modifier: Modifier = Modifier
) {
    val headerStringResource =
        if (isLogin) R.string.login_header_text
        else R.string.register_header_text

    val headerText = stringResource(id = headerStringResource)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
//            colorFilter = ColorFilter.tint(
//                MaterialTheme.colorScheme.primary
//            ),
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(id = R.dimen.auth_component_padding)
                )
                .size(
                    width = 160.dp,
                    height = 40.dp
                )
        )
        Text(
            text = headerText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AuthSnackbar(
    snackbarHostState: SnackbarHostState,
    toastType: TOAST_TYPE,
    modifier: Modifier = Modifier
) {
    val containerColor = when (toastType) {
        TOAST_TYPE.FAILURE -> MaterialTheme.colorScheme.errorContainer
        else -> SnackbarDefaults.color
    }

    val contentColor = when (toastType) {
        TOAST_TYPE.FAILURE -> MaterialTheme.colorScheme.onErrorContainer
        else -> SnackbarDefaults.contentColor
    }

    val dismissActionContentColor = when (toastType) {
        TOAST_TYPE.FAILURE -> MaterialTheme.colorScheme.onErrorContainer
        else -> SnackbarDefaults.dismissActionContentColor
    }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    ) {
        Snackbar(
            snackbarData = it,
            containerColor = containerColor,
            contentColor = contentColor,
            dismissActionContentColor = dismissActionContentColor
        )
    }
}

@Composable
fun AuthEmailField(
    value: String,
    onValueChange: (String) -> Unit,
    error: AUTH_ERROR,
    @StringRes label: Int,
    @DrawableRes fieldIcon: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = label)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = error != AUTH_ERROR.NONE,
            supportingText = {
                Text(text = stringResource(id = error.errorText))
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = fieldIcon),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AuthPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    error: AUTH_ERROR,
    onDone: () -> Unit,
    @StringRes label: Int,
    @DrawableRes fieldIcon: Int,
    modifier: Modifier = Modifier
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = label)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = error != AUTH_ERROR.NONE,
            supportingText = {
                Text(text = stringResource(id = error.errorText))
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = fieldIcon),
                    contentDescription = null
                )
            },
            trailingIcon = {
                AuthPasswordFieldVisibilityIcon(
                    showPassword = showPassword,
                    toggleVisibility = { showPassword = !showPassword }
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AuthPasswordFieldVisibilityIcon(
    showPassword: Boolean,
    toggleVisibility: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showPassword) {
        IconButton(
            onClick = toggleVisibility,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.no_visibility_24),
                contentDescription = null
            )
        }
    } else {
        IconButton(
            onClick = toggleVisibility,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.visibility_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AuthSubmitButton(
    isLogin: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonStringResource =
        if (isLogin) R.string.login_text
            else R.string.register_text

    val buttonText = stringResource(id = buttonStringResource)

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.auth_button_shape_size)
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AuthSocialButton(
    @DrawableRes socialIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.auth_social_border_width),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.auth_button_shape_size)
                )
            )
    ) {
        Icon(
            painter = painterResource(id = socialIcon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}

@Composable
fun AuthSocialRow(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.auth_social_padding)
            ),
        ) {
            AuthSocialButton(
                socialIcon = R.drawable.google_icon,
                onClick = { /*TODO*/ }
            )
            AuthSocialButton(
                socialIcon = R.drawable.facebook_icon,
                onClick = { /*TODO*/ }
            )
            AuthSocialButton(
                socialIcon = R.drawable.instagram_icon,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun AuthSwitch(
    isLogin: Boolean,
    onAuthSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infoStringResource =
        if (isLogin) R.string.info_register_text
            else R.string.info_login_text

    val infoText = stringResource(id = infoStringResource)

    val switchAuthStringResource =
        if (isLogin) R.string.register_text
            else R.string.login_text

    val switchAuthText = stringResource(id = switchAuthStringResource)

    Row(
        modifier = modifier
    ) {
        Text(
            text = infoText
        )
        Text(
            text = switchAuthText,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.auth_info_padding)
                )
                .clickable { onAuthSwitch() }
        )
    }
}

@Composable
fun AuthFooter(
    isLogin: Boolean,
    onAuthSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.auth_footer_padding)
        ),
        modifier = modifier
    ) {
        AuthSocialRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        AuthSwitch(
            isLogin = isLogin,
            onAuthSwitch = onAuthSwitch
        )
    }
}