package com.example.hairbook.feature.favourites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.component.HairstyleCard
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.feature.favourites.R

private data class FavSample(
    val name: String,
    val tag: String,
    val aspectRatio: Float,
    val accentColor: Color,
)

// Demo: show two saved styles. In production this comes from Room via ViewModel.
private val savedSamples = listOf(
    FavSample("Classic Fade", "Fade · Short", 1.25f, Color(0xFF4A3020)),
    FavSample("French Bob", "Bob · Short", 1.1f, Color(0xFF3A1E35)),
)

@Composable
fun FavouritesScreen(
    onNavigateToDetail: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val hasFavourites = savedSamples.isNotEmpty()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = { HairBookTopBar(title = stringResource(R.string.favourites_title)) },
    ) { innerPadding ->
        if (hasFavourites) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp,
            ) {
                items(savedSamples) { style ->
                    HairstyleCard(
                        name = style.name,
                        tag = style.tag,
                        accentColor = style.accentColor,
                        onClick = onNavigateToDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(style.aspectRatio),
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = GoldMuted,
                        modifier = Modifier.size(64.dp),
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.favourites_empty_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Gold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.favourites_empty_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = GreyLight,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
