package com.example.hairbook.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.theme.Black
import com.example.hairbook.core.ui.theme.Gold
import com.example.hairbook.core.ui.theme.GoldAlpha12
import com.example.hairbook.core.ui.theme.GoldMuted
import com.example.hairbook.core.ui.theme.GreyMuted

private val PillShape = RoundedCornerShape(24.dp)

@Composable
fun HairBookButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        enabled = enabled,
        shape = PillShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Gold,
            contentColor = Black,
            disabledContainerColor = GoldMuted,
            disabledContentColor = GreyMuted,
        ),
    ) {
        Text(text = text, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun HairBookSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        enabled = enabled,
        shape = PillShape,
        border = BorderStroke(1.dp, if (enabled) Gold else GoldMuted),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Gold,
            containerColor = GoldAlpha12,
        ),
    ) {
        Text(text = text, fontWeight = FontWeight.Medium)
    }
}
