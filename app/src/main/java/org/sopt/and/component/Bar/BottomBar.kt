package org.sopt.and.component.Bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.sopt.and.navigate.Screen
import org.sopt.and.navigate.ScreenTab

@Composable
fun BottomBar(
    selected: Screen,
    navController: NavHostController
) {
    val imageSize: Dp = 25.dp
    val fontSize = 12.sp

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            Row(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScreenTab.values().forEach { tab ->
                    val isSelected = (tab.name == selected.name)
                    val image = if (isSelected) tab.selectedImage else tab.unselectedImage
                    val color = if (isSelected) tab.selectedColor else tab.unselectedColor

                    BottomTab(
                        navController = navController,
                        route = tab.route,
                        image = image,
                        textColor = color,
                        imageSize = imageSize,
                        fontSize = fontSize,
                        text = tab.label
                    )
                }
            }
        }
    }
}

@Composable
fun BottomTab(
    navController: NavHostController,
    route: String,
    image: Int,
    textColor: Int,
    imageSize: Dp,
    fontSize: TextUnit,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                navController.navigate(route) {
                    popUpTo(route) {
                        inclusive = true
                    }
                }
            }
            .size(80.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
        )
        Text(
            text = text,
            fontSize = fontSize,
            color = colorResource(id = textColor)
        )
    }
}