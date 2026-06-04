package com.example.hairbook.feature.browse.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.CategoryCard
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.feature.browse.R

private data class CategorySample(
    val id: String,
    val name: String,
    val gender: String,
    val count: Int,
    val aspectRatio: Float,
    val accentColor: Color,
    @DrawableRes val imageRes: Int,
)

private val allCategories = listOf(
    CategorySample("fade", "Fade", "MEN", 5, 0.85f, Color(0xFF3A2010), R.drawable.category_fade),
    CategorySample("undercut", "Undercut", "MEN", 3, 1.1f, Color(0xFF1A2A1E), R.drawable.category_undercut),
    CategorySample("textured", "Textured", "MEN", 2, 0.9f, Color(0xFF1E2030), R.drawable.category_textured),
    CategorySample("taper", "Taper", "MEN", 1, 1.0f, Color(0xFF251A10), R.drawable.category_taper),
    CategorySample("bob", "Bob", "WOMEN", 2, 0.9f, Color(0xFF2A1020), R.drawable.category_bob),
    CategorySample("curls", "Curls", "WOMEN", 1, 1.15f, Color(0xFF102818), R.drawable.category_curls),
    CategorySample("layers", "Layers", "WOMEN", 2, 0.95f, Color(0xFF201A10), R.drawable.category_layers),
)

@Composable
fun HomeScreen(
    onNavigateToCategory: (categoryId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(R.string.browse_men), stringResource(R.string.browse_women))
    val genderKeys = listOf("MEN", "WOMEN")

    val displayed = allCategories.filter { it.gender == genderKeys[selectedTab] }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = stringResource(R.string.home_title)) },
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

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp,
            ) {
                items(displayed) { category ->
                    CategoryCard(
                        name = category.name,
                        count = stringResource(R.string.home_category_count, category.count),
                        coverImage = null,
                        imageRes = category.imageRes,
                        onClick = { onNavigateToCategory(category.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(category.aspectRatio),
                    )
                }
            }
        }
    }
}
