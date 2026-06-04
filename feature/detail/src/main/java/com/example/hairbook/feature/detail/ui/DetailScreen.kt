package com.example.hairbook.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookImage
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha24
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.detail.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    onNavigateUp: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var isFavourited by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = {
            HairBookTopBar(
                title = "Classic Fade",
                onNavigateUp = onNavigateUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isFavourited = !isFavourited },
                containerColor = Gold,
                contentColor = Black,
            ) {
                Icon(
                    imageVector = if (isFavourited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.detail_save),
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            // Hero image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
            ) {
                HairBookImage(
                    model = null,
                    contentDescription = "Classic Fade",
                    modifier = Modifier.fillMaxSize(),
                    placeholderColor = Color(0xFF4A3020).copy(alpha = 0.8f),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.5f to Color.Transparent,
                                    1.0f to Black,
                                ),
                            ),
                        ),
                )
                Text(
                    text = "Men · Short",
                    style = MaterialTheme.typography.labelLarge,
                    color = Gold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .background(GoldAlpha24, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                )
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Classic Fade",
                    style = MaterialTheme.typography.headlineLarge,
                    color = White,
                )

                Spacer(Modifier.height(12.dp))

                // Attribute chips
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    listOf("Oval", "Round", "Square", "Straight", "Wavy", "Short").forEach { attr ->
                        SuggestionChip(
                            onClick = {},
                            label = { Text(attr) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = GoldAlpha24,
                                labelColor = Gold,
                            ),
                            border = SuggestionChipDefaults.suggestionChipBorder(
                                enabled = true,
                                borderColor = Color.Transparent,
                            ),
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
                HorizontalDivider(color = GoldMuted)
                Spacer(Modifier.height(20.dp))

                // About section
                Text(
                    text = stringResource(R.string.detail_about),
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "A timeless barbershop cut featuring a gradual skin fade on the sides and back. The top stays longer for versatile styling — from a neat side part to a casual textured finish. Works on almost every face shape and hair type.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GreyLight,
                )

                Spacer(Modifier.height(24.dp))
                HorizontalDivider(color = GoldMuted)
                Spacer(Modifier.height(20.dp))

                // Time & Effort section
                Text(
                    text = stringResource(R.string.detail_time_effort),
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                )
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.detail_time_label),
                            style = MaterialTheme.typography.labelLarge,
                            color = GreyLight,
                        )
                        Text(
                            text = "30 ${stringResource(R.string.detail_minutes)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = White,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.detail_difficulty),
                            style = MaterialTheme.typography.labelLarge,
                            color = GreyLight,
                        )
                        Row {
                            repeat(5) { index ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = if (index < 2) Gold else GoldMuted,
                                    modifier = Modifier.size(18.dp),
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))
                HorizontalDivider(color = GoldMuted)
                Spacer(Modifier.height(20.dp))

                // Products section
                Text(
                    text = stringResource(R.string.detail_products),
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                )
                Spacer(Modifier.height(12.dp))
                listOf("Matte Pomade" to "Styling", "Barber Shampoo" to "Cleansing").forEach { (name, type) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(name, style = MaterialTheme.typography.bodyLarge, color = White)
                        Text(
                            text = type,
                            style = MaterialTheme.typography.labelMedium,
                            color = Gold,
                            modifier = Modifier
                                .background(GoldAlpha24, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                        )
                    }
                    HorizontalDivider(color = GoldMuted.copy(alpha = 0.4f))
                }

                Spacer(Modifier.height(80.dp))
            }
        }
    }
}
