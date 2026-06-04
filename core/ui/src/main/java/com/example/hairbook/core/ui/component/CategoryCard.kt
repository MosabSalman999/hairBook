package com.example.hairbook.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha12
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.SurfaceDark

@Composable
fun CategoryCard(
    name: String,
    count: String,
    coverImage: Any?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholderColor: Color = SurfaceDark,
    @DrawableRes imageRes: Int? = null,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            } else {
                HairBookImage(
                    model = coverImage,
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize(),
                    placeholderColor = placeholderColor,
                )
            }
            // Bottom gradient scrim for text legibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.3f to Color.Transparent,
                                1.0f to Black.copy(alpha = 0.85f),
                            ),
                        ),
                    ),
            )
            // Count badge — top end
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(GoldAlpha12, shape = MaterialTheme.shapes.extraSmall)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = count,
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldMuted,
                )
            }
            // Category name — bottom start
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = Gold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
            )
        }
    }
}
