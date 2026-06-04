package com.example.hairbook.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookButton
import com.example.hairbook.core.ui.component.HairBookSecondaryButton
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.auth.R

@Composable
fun AuthScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Gold,
        unfocusedBorderColor = GreyMuted,
        focusedLabelColor = Gold,
        unfocusedLabelColor = GreyLight,
        cursorColor = Gold,
        focusedTextColor = White,
        unfocusedTextColor = White,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.auth_title),
            style = MaterialTheme.typography.displayMedium,
            color = Gold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(R.string.auth_tagline),
            style = MaterialTheme.typography.bodyMedium,
            color = GreyLight,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp),
        )

        Spacer(Modifier.height(48.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.auth_email)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = fieldColors,
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.auth_password)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = fieldColors,
        )

        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.End),
        ) {
            Text(
                text = stringResource(R.string.auth_forgot_password),
                color = GreyLight,
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(Modifier.height(16.dp))

        HairBookButton(
            text = stringResource(R.string.auth_sign_in),
            onClick = onContinue,
        )

        Spacer(Modifier.height(12.dp))

        HairBookSecondaryButton(
            text = stringResource(R.string.auth_continue_guest),
            onClick = onContinue,
        )

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.auth_no_account),
                color = GreyLight,
                style = MaterialTheme.typography.bodyMedium,
            )
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(R.string.auth_register),
                    color = Gold,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
