package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.img_main_logo),
            contentDescription = "",
            modifier = Modifier.width(100.dp)
        )

        Row{
            Image(
                painter = painterResource(R.drawable.outline_cast_connected_24),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(28.dp)
            )
            Image(
                painter = painterResource(R.drawable.outline_live_tv_24),
                contentDescription = "",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}