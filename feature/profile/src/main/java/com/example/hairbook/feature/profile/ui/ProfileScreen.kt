package com.example.hairbook.feature.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookButton
import com.example.hairbook.core.ui.component.HairBookSecondaryButton
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha24
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.profile.R

@Composable
fun ProfileScreen(
    onNavigateToAuth: () -> Unit = {},
    onNavigateToAdmin: () -> Unit = {},
    onNavigateToBooking: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = { HairBookTopBar(title = stringResource(R.string.profile_title)) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
        ) {
            Spacer(Modifier.height(24.dp))

            // Avatar + user info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(GoldAlpha24, shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Gold,
                        modifier = Modifier.size(40.dp),
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.profile_guest_user),
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                    )
                    Text(
                        text = stringResource(R.string.profile_not_signed_in),
                        style = MaterialTheme.typography.bodyMedium,
                        color = GreyLight,
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Language selector
            Text(
                text = stringResource(R.string.profile_language),
                style = MaterialTheme.typography.labelLarge,
                color = GreyLight,
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                listOf("EN", "AR", "DE").forEach { lang ->
                    val isSelected = lang == "EN"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                if (isSelected) GoldAlpha24 else SurfaceDark,
                                shape = MaterialTheme.shapes.small,
                            )
                            .clickable {}
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = lang,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (isSelected) Gold else GreyLight,
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))
            HorizontalDivider(color = GoldMuted.copy(alpha = 0.4f))
            Spacer(Modifier.height(4.dp))

            // Navigation rows
            listOf(
                Triple(R.string.profile_admin_panel, onNavigateToAdmin, true),
                Triple(R.string.profile_booking, onNavigateToBooking, false),
            ).forEach { (labelRes, action, isAdmin) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { action() }
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(labelRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = White,
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = GoldMuted,
                    )
                }
                HorizontalDivider(color = GoldMuted.copy(alpha = 0.4f))
            }

            Spacer(Modifier.height(32.dp))

            HairBookButton(
                text = stringResource(R.string.profile_sign_in),
                onClick = onNavigateToAuth,
            )
            Spacer(Modifier.height(12.dp))
            HairBookSecondaryButton(
                text = stringResource(R.string.profile_sign_out),
                onClick = onNavigateToAuth,
            )

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Hair Book v1.0",
                style = MaterialTheme.typography.bodySmall,
                color = GoldMuted,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
