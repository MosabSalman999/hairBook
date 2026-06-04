package com.example.hairbook.feature.admin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Error
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha24
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.admin.R

private data class AdminStyleRow(
    val name: String,
    val gender: String,
    val count: Int,
)

private val adminSamples = listOf(
    AdminStyleRow("Classic Fade", "MEN", 1),
    AdminStyleRow("Textured Waves", "MEN", 2),
    AdminStyleRow("Undercut", "MEN", 3),
    AdminStyleRow("French Bob", "WOMEN", 4),
    AdminStyleRow("Layered Curls", "WOMEN", 5),
)

@Composable
fun AdminScreen(
    onNavigateUp: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = {
            HairBookTopBar(
                title = stringResource(R.string.admin_title),
                onNavigateUp = onNavigateUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Gold,
                contentColor = Black,
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.admin_add))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(R.string.admin_styles_count, adminSamples.size),
                style = MaterialTheme.typography.bodyMedium,
                color = GreyLight,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )
            HorizontalDivider(color = GoldMuted.copy(alpha = 0.4f))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(adminSamples) { index, style ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (index % 2 == 0) Black else SurfaceDark)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = style.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = White,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = style.gender,
                                style = MaterialTheme.typography.labelMedium,
                                color = Gold,
                                modifier = Modifier
                                    .background(GoldAlpha24, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                            )
                        }
                        Row {
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = stringResource(R.string.admin_edit),
                                    tint = Gold,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = stringResource(R.string.admin_delete),
                                    tint = Error,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }
                    }
                    HorizontalDivider(color = GoldMuted.copy(alpha = 0.3f))
                }
            }
        }
    }
}
