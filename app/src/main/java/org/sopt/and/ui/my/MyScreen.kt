package org.sopt.and.ui.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.component.BuyTextButton
import org.sopt.and.component.MyPageItem
import org.sopt.and.ui.signin.SignInViewModel

@Composable
fun MyScreen(
    navController: NavHostController,
    signInViewModel: SignInViewModel
) {
    val userId = signInViewModel.userInfo.userId

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(20.dp)
        ) {
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
                    text = stringResource(R.string.my_nickname, userId),
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

            BuyTextButton(
                labelText = stringResource(R.string.my_text1),
                onClick = {
                    TODO("결제창으로 넘어가기")
                }
            )
            Spacer(Modifier.height(20.dp))

            BuyTextButton(
                labelText = stringResource(R.string.my_text2),
                onClick = {
                    TODO("결제창으로 넘어가기")
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
        ) {
            item {
                MyPageItem(
                    labelText = stringResource(R.string.my_all),
                    icon = painterResource(R.drawable.baseline_info_outline_24),
                    iconText = stringResource(R.string.my_all_none)
                )
            }

            item {
                MyPageItem(
                    labelText = stringResource(R.string.my_interest),
                    icon = painterResource(R.drawable.baseline_info_outline_24),
                    iconText = stringResource(R.string.my_interest_none)
                )
            }
        }
    }
}

