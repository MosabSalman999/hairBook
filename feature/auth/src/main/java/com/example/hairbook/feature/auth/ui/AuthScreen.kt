package com.example.hairbook.feature.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookButton
import com.example.hairbook.feature.auth.R

@Composable
fun AuthScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.auth_title), style = MaterialTheme.typography.headlineLarge)
        Text(
            text = stringResource(R.string.auth_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        HairBookButton(
            text = stringResource(R.string.auth_continue),
            onClick = onContinue
        )
    }
}
