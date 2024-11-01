package org.sopt.and.ui.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.component.Bar.BottomBar
import org.sopt.and.component.IconWithTitle
import org.sopt.and.component.MyPageList
import org.sopt.and.navigate.Screen
import org.sopt.and.ui.signin.SignInViewModel

@Composable
fun MyScreen(
    navController: NavHostController,
    signInViewModel: SignInViewModel
) {
    val userId = signInViewModel.userInfo.userId
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomBar(Screen.MY_PAGE, navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
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

                IconWithTitle(
                    labelText = stringResource(R.string.my_text1),
                    actionText = stringResource(R.string.my_buy),
                    actionIcon = painterResource(R.drawable.baseline_navigate_next_24)
                ) {
                    TODO("결제창으로 넘어가기")
                }
                Spacer(Modifier.height(20.dp))

                IconWithTitle(
                    labelText = stringResource(R.string.my_text2),
                    actionText = stringResource(R.string.my_buy),
                    actionIcon = painterResource(R.drawable.baseline_navigate_next_24)
                ) {
                    TODO("결제창으로 넘어가기")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .verticalScroll(scrollState)
                    .padding(20.dp)
            ){
                MyPageList(
                    labelText = stringResource(R.string.my_all),
                    icon = painterResource(R.drawable.baseline_info_outline_24),
                    iconText = stringResource(R.string.my_all_none)
                )

                MyPageList(
                    labelText = stringResource(R.string.my_interest),
                    icon = painterResource(R.drawable.baseline_info_outline_24),
                    iconText = stringResource(R.string.my_interest_none)
                )
            }
        }
    }
}