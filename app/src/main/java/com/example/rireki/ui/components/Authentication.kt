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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rireki.R
import com.example.rireki.ui.theme.RirekiTheme

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
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
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
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = fieldIcon),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
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

@Preview
@Composable
fun AuthFooterPreview() {
    RirekiTheme {
        AuthFooter(
            isLogin = true,
            onAuthSwitch = { }
        )
    }
}

@Preview
@Composable
fun AuthSocialRowPreview() {
    RirekiTheme {
        AuthSocialRow()
    }
}

@Preview
@Composable
fun AuthSocialButtonPreview() {
    RirekiTheme {
        AuthSocialButton(
            socialIcon = R.drawable.google_icon,
            onClick = { /*TODO*/ }
        )
    }
}

@Preview
@Composable
fun AuthSubmitButtonPreview() {
    RirekiTheme {
        AuthSubmitButton(
            isLogin = true,
            onClick = { /*TODO*/ }
        )
    }
}

@Preview
@Composable
fun AuthLabelPreview() {
    RirekiTheme {
        AuthTextField(
            value = "",
            onValueChange = { },
            label = R.string.email_label,
            fieldIcon = R.drawable.mail_24
        )
    }
}

@Preview
@Composable
fun AuthHeaderPreview() {
    RirekiTheme {
        AuthHeader(isLogin = true)
    }
}