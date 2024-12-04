package org.sopt.and.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun InfoTextWithIcon(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Gray,
    spacing: Dp = 5.dp
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.baseline_info_outline_24),
            contentDescription = null
        )
        Spacer(Modifier.width(spacing))
        Text(
            text = text,
            color = textColor
        )
    }
}