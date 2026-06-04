package com.example.hairbook.feature.finder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookButton
import com.example.hairbook.core.ui.component.HairBookSecondaryButton
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha12
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyLight
import com.example.hairbook.core.ui.theme.GreyMuted
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.core.ui.theme.White
import com.example.hairbook.feature.finder.R

private data class FinderStep(
    val questionRes: Int,
    val options: List<Pair<String, Color>>,
)

@Composable
fun FinderScreen(modifier: Modifier = Modifier) {
    val steps = listOf(
        FinderStep(
            R.string.finder_question_gender,
            listOf("Men" to Color(0xFF1E3040), "Women" to Color(0xFF3A1E35)),
        ),
        FinderStep(
            R.string.finder_question_face,
            listOf(
                "Oval" to Color(0xFF1A2E2A),
                "Round" to Color(0xFF2A2A3A),
                "Square" to Color(0xFF3A2010),
                "Heart" to Color(0xFF301520),
                "Diamond" to Color(0xFF102030),
                "Oblong" to Color(0xFF201A10),
            ),
        ),
        FinderStep(
            R.string.finder_question_texture,
            listOf(
                "Straight" to Color(0xFF1E2030),
                "Wavy" to Color(0xFF1E3020),
                "Curly" to Color(0xFF302010),
                "Coily" to Color(0xFF201020),
            ),
        ),
        FinderStep(
            R.string.finder_question_length,
            listOf(
                "Short" to Color(0xFF2A1A0A),
                "Medium" to Color(0xFF0A2A1A),
                "Long" to Color(0xFF1A0A2A),
            ),
        ),
        FinderStep(
            R.string.finder_question_thickness,
            listOf(
                "Fine" to Color(0xFF1A1A2A),
                "Medium" to Color(0xFF1A2A1A),
                "Thick" to Color(0xFF2A1A1A),
            ),
        ),
        FinderStep(
            R.string.finder_question_colour,
            listOf(
                "Black" to Color(0xFF0A0A10),
                "Brown" to Color(0xFF3A2010),
                "Blonde" to Color(0xFF3A2A00),
                "Red" to Color(0xFF3A1000),
                "Grey" to Color(0xFF2A2A2A),
                "Coloured" to Color(0xFF1A0A3A),
            ),
        ),
    )

    var currentStep by remember { mutableIntStateOf(0) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    val totalSteps = steps.size
    val progress = (currentStep + 1).toFloat() / totalSteps

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Black,
        topBar = { HairBookTopBar(title = stringResource(R.string.finder_title)) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Spacer(Modifier.height(12.dp))

            // Step counter + progress bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.finder_step, currentStep + 1, totalSteps),
                    style = MaterialTheme.typography.labelLarge,
                    color = GreyLight,
                )
                Text(
                    text = "${((progress) * 100).toInt()}%",
                    style = MaterialTheme.typography.labelLarge,
                    color = Gold,
                )
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(3.dp),
                color = Gold,
                trackColor = GoldMuted.copy(alpha = 0.3f),
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(steps[currentStep].questionRes),
                style = MaterialTheme.typography.headlineMedium,
                color = White,
                textAlign = TextAlign.Start,
            )

            Spacer(Modifier.height(20.dp))

            val options = steps[currentStep].options
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(options) { index, (label, color) ->
                    val isSelected = selectedIndex == index
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) Gold else GreyMuted,
                                shape = MaterialTheme.shapes.medium,
                            )
                            .clickable {
                                selectedIndex = if (selectedIndex == index) null else index
                            },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(color.copy(alpha = 0.8f), SurfaceDark),
                                    ),
                                ),
                        )
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(GoldAlpha12),
                            )
                        }
                        Text(
                            text = label,
                            style = MaterialTheme.typography.titleMedium,
                            color = White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(12.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            if (currentStep < totalSteps - 1) {
                HairBookButton(
                    text = stringResource(R.string.finder_next),
                    onClick = {
                        currentStep++
                        selectedIndex = null
                    },
                    enabled = selectedIndex != null,
                )
            } else {
                HairBookButton(
                    text = stringResource(R.string.finder_see_results),
                    onClick = {},
                    enabled = selectedIndex != null,
                )
            }

            if (currentStep > 0) {
                Spacer(Modifier.height(8.dp))
                HairBookSecondaryButton(
                    text = stringResource(R.string.finder_back),
                    onClick = {
                        currentStep--
                        selectedIndex = null
                    },
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
