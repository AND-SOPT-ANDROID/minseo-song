package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconWithText(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Gray,
    spacing: Dp = 5.dp
) {
    Row(modifier = modifier) {
        Image(
            painter = icon,
            contentDescription = null
        )
        Spacer(Modifier.width(spacing))
        Text(
            text = text,
            color = textColor
        )
    }
}