package com.example.hairbook.core.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.hairbook.core.ui.theme.SurfaceDark
import com.example.hairbook.core.ui.theme.SurfaceElevated

@Composable
fun HairBookImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color = SurfaceDark,
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = ColorPainter(placeholderColor),
        error = ColorPainter(SurfaceElevated),
        modifier = modifier,
    )
}
