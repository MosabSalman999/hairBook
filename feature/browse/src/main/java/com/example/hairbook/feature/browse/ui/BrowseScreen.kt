package com.example.hairbook.feature.browse.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.component.HairstyleCard
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha24
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.core.ui.theme.SurfaceElevated
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.browse.R

private data class StyleSample(
    val id: String,
    val name: String,
    val tag: String,
    val gender: String,
    val aspectRatio: Float,
    val accentColor: Color,
)

private val allSamples = listOf(
    StyleSample("1", "Classic Fade", "Fade · Short", "MEN", 1.25f, Color(0xFF4A3020)),
    StyleSample("2", "Textured Waves", "Wavy · Medium", "MEN", 1.0f, Color(0xFF1E3040)),
    StyleSample("3", "Undercut", "Bold · Medium", "MEN", 1.4f, Color(0xFF1A2A1E)),
    StyleSample("4", "Slick Back", "Straight · Long", "MEN", 1.1f, Color(0xFF2A2A3A)),
    StyleSample("5", "French Bob", "Bob · Short", "WOMEN", 1.2f, Color(0xFF3A1E35)),
    StyleSample("6", "Layered Curls", "Curly · Long", "WOMEN", 1.45f, Color(0xFF1E3018)),
    StyleSample("7", "Sleek Lob", "Straight · Medium", "WOMEN", 0.95f, Color(0xFF302810)),
    StyleSample("8", "Curtain Bangs", "Wavy · Long", "WOMEN", 1.3f, Color(0xFF251A30)),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseScreen(
    onNavigateToDetail: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedFilter by remember { mutableStateOf("All") }
    val tabs = listOf(stringResource(R.string.browse_men), stringResource(R.string.browse_women))
    val genderKeys = listOf("MEN", "WOMEN")
    val filters = listOf(
        stringResource(R.string.browse_filter_all),
        stringResource(R.string.browse_filter_curly),
        stringResource(R.string.browse_filter_wavy),
        stringResource(R.string.browse_filter_straight),
        stringResource(R.string.browse_filter_short),
        stringResource(R.string.browse_filter_medium),
        stringResource(R.string.browse_filter_long),
    )

    val displayed = allSamples.filter { it.gender == genderKeys[selectedTab] }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = stringResource(R.string.browse_title)) },
        containerColor = Black,
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Black,
                contentColor = Gold,
                indicator = { tabPositions ->
                    if (selectedTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = Gold,
                        )
                    }
                },
            ) {
                tabs.forEachIndexed { index, label ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = label,
                                color = if (selectedTab == index) Gold else GreyMuted,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        },
                    )
                }
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GoldAlpha24,
                            selectedLabelColor = Gold,
                            containerColor = SurfaceElevated,
                            labelColor = White,
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selectedFilter == filter,
                            selectedBorderColor = Color.Transparent,
                            borderColor = GreyMuted,
                        ),
                    )
                }
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp,
            ) {
                items(displayed) { style ->
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
        }
    }
}
