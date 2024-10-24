package org.sopt.and.component

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
import org.sopt.and.R
import org.sopt.and.model.Routes

@Composable
fun BottomBar(
    selected: Int,
    navController: NavHostController
) {
    val imageSize : Dp = 25.dp
    val fontSize = 12.sp

    var homeImage = R.drawable.outline_home_24_gray
    var homeColor = R.color.gray
    var searchImage = R.drawable.outline_search_24_gray
    var searchColor = R.color.gray
    val myImage = R.drawable.img_profile_select
    var myColor = R.color.gray

    when (selected) {
        1 -> {
            homeImage = R.drawable.outline_home_24_white
            homeColor = R.color.white
        }

        2 -> {
            searchImage = R.drawable.outline_search_24_white
            searchColor = R.color.white
        }

        3 -> {
            myColor = R.color.white
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column {
            Row(
                modifier = Modifier
                    .clickable {}
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(80.dp)
                ,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                CreateTap(navController, Routes.Home.route, homeImage, homeColor, imageSize, fontSize, "홈")
                CreateTap(navController, Routes.Search.route, searchImage, searchColor, imageSize, fontSize, "검색")
                CreateTap(navController, Routes.My.route, myImage, myColor, imageSize, fontSize, "MY")
            }
        }
    }
}

@Composable
fun CreateTap(navController: NavHostController, route:String, image:Int, textColor:Int, imageSize:Dp, fontSize: TextUnit, text:String){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                navController.navigate(route){
                    popUpTo(route){
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