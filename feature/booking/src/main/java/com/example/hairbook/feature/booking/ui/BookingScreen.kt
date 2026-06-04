package com.example.hairbook.feature.booking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookButton
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha24
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.booking.R

@Composable
fun BookingScreen(
    onNavigateUp: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = {
            HairBookTopBar(
                title = stringResource(R.string.booking_title),
                onNavigateUp = onNavigateUp,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(GoldAlpha24, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Gold,
                    modifier = Modifier.size(52.dp),
                )
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.booking_coming_soon_title),
                style = MaterialTheme.typography.displayMedium,
                color = Gold,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.booking_coming_soon_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = GreyLight,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(48.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.booking_notify_email_hint)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Gold,
                    unfocusedBorderColor = GreyMuted,
                    focusedLabelColor = Gold,
                    unfocusedLabelColor = GreyLight,
                    cursorColor = Gold,
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                ),
            )

            Spacer(Modifier.height(16.dp))

            HairBookButton(
                text = stringResource(R.string.booking_notify_me),
                onClick = {},
            )
        }
    }
}
