package org.sopt.and.ui.my

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.ui.signin.ID_KEY
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun MyScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val intent = (context as Activity).intent
    val userId = intent.getStringExtra(ID_KEY).orEmpty()

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(20.dp)
        ){
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_profile_select),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "$userId" + stringResource(R.string.my_nickname),
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(R.drawable.outline_notifications_24),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 20.dp)
                )

                Image(
                    painter = painterResource(R.drawable.outline_settings_24),
                    contentDescription = ""
                )

            }

            Text(
                text = stringResource(R.string.my_text1),
                color = Color.LightGray
            )
            Text(
                text = stringResource(R.string.my_buy),
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Text(
                text = stringResource(R.string.my_text2),
                color = Color.LightGray
            )
            Text(
                text = stringResource(R.string.my_buy),
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
        ){
            Text(
                text = stringResource(R.string.my_all),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            Column(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.baseline_info_outline_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(80.dp)
                )
                Text(
                    text = stringResource(R.string.my_all_none),
                    color = Color.LightGray
                )
            }

            Text(
                text = stringResource(R.string.my_interest),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.baseline_info_outline_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(80.dp)
                )
                Text(
                    text = stringResource(R.string.my_interest_none),
                    color = Color.LightGray
                )
            }
        }
    }
}