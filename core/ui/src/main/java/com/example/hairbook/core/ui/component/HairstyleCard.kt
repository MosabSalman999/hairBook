package com.example.hairbook.core.ui.component

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
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha12
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.core.ui.theme.White

@Composable
fun HairstyleCard(
    name: String,
    tag: String,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: Any? = null,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            HairBookImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier.fillMaxSize(),
                placeholderColor = accentColor.copy(alpha = 0.65f),
            )
            // Legibility scrim over bottom half
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.35f to Color.Transparent,
                                1.0f to Black.copy(alpha = 0.88f),
                            ),
                        ),
                    ),
            )
            // Tag chip — top end
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(GoldAlpha12, shape = MaterialTheme.shapes.extraSmall)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gold,
                )
            }
            // Style name — bottom start
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
            )
        }
    }
}
