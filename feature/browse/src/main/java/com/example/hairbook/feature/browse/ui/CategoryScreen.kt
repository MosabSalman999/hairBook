package com.example.hairbook.feature.browse.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.component.HairstyleCard
import com.example.hairbook.core.ui.theme.Black

private data class HaircutSample(
    val id: String,
    val name: String,
    val tag: String,
    val aspectRatio: Float,
    val accentColor: Color,
)

private val samplesByCategory = mapOf(
    "fade" to listOf(
        HaircutSample("hs-001", "Classic Fade", "Fade · Short", 1.25f, Color(0xFF4A3020)),
        HaircutSample("hs-006", "Low Fade", "Fade · Short", 1.0f, Color(0xFF3A2515)),
        HaircutSample("hs-007", "High Fade", "Fade · Short", 1.35f, Color(0xFF2A1E12)),
        HaircutSample("hs-008", "Skin Fade", "Fade · Short", 1.1f, Color(0xFF3A2810)),
        HaircutSample("hs-009", "Drop Fade", "Fade · Medium", 1.2f, Color(0xFF4A3218)),
    ),
    "undercut" to listOf(
        HaircutSample("hs-003", "Undercut", "Bold · Medium", 1.4f, Color(0xFF1A2A1E)),
        HaircutSample("hs-003b", "Textured Undercut", "Bold · Long", 1.05f, Color(0xFF1E2A18)),
        HaircutSample("hs-003c", "Slick Undercut", "Sleek · Medium", 1.25f, Color(0xFF152018)),
    ),
    "textured" to listOf(
        HaircutSample("hs-002", "Textured Waves", "Wavy · Medium", 1.0f, Color(0xFF1E3040)),
        HaircutSample("hs-002b", "Messy Texture", "Wavy · Short", 1.3f, Color(0xFF182030)),
    ),
    "taper" to listOf(
        HaircutSample("hs-010", "Classic Taper", "Taper · Short", 1.15f, Color(0xFF2A2015)),
    ),
    "bob" to listOf(
        HaircutSample("hs-004", "French Bob", "Bob · Short", 1.2f, Color(0xFF3A1E35)),
        HaircutSample("hs-011", "Blunt Bob", "Bob · Short", 0.95f, Color(0xFF2A1825)),
    ),
    "curls" to listOf(
        HaircutSample("hs-005", "Layered Curls", "Curly · Long", 1.45f, Color(0xFF1E3018)),
    ),
    "layers" to listOf(
        HaircutSample("hs-012", "Curtain Bangs", "Layers · Long", 1.1f, Color(0xFF251A30)),
        HaircutSample("hs-013", "Long Layers", "Layers · Long", 1.3f, Color(0xFF1E2018)),
    ),
)

private fun categoryDisplayName(categoryId: String): String = when (categoryId) {
    "fade" -> "Fade"
    "undercut" -> "Undercut"
    "textured" -> "Textured"
    "taper" -> "Taper"
    "bob" -> "Bob"
    "curls" -> "Curls"
    "layers" -> "Layers"
    "braids" -> "Braids"
    else -> categoryId.replaceFirstChar { it.uppercase() }
}

@Composable
fun CategoryScreen(
    categoryId: String,
    onNavigateToDetail: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val styles = samplesByCategory[categoryId] ?: emptyList()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HairBookTopBar(
                title = categoryDisplayName(categoryId),
                onNavigateUp = onNavigateUp,
            )
        },
        containerColor = Black,
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
        ) {
            items(styles) { haircut ->
                HairstyleCard(
                    name = haircut.name,
                    tag = haircut.tag,
                    accentColor = haircut.accentColor,
                    onClick = onNavigateToDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(haircut.aspectRatio),
                )
            }
        }
    }
}
