package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun BuyTextButton(
    labelText: String,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = labelText,
            color = Color.LightGray
        )
        Row(
            modifier = Modifier
                .clickable { onClick }
        ) {
            Text(
                text = stringResource(R.string.my_buy),
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Image(
                painter = painterResource(R.drawable.baseline_navigate_next_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}