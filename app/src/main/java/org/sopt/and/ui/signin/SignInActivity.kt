package org.sopt.and.ui.signin

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.component.IDTextField
import org.sopt.and.component.PasswordTextField
import org.sopt.and.model.Routes

const val ID_KEY = "id"
const val PASSWORD_KEY = "password"

@Composable
fun SignInScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel()
) {
    val userId by viewModel::userId
    val userPassword by viewModel::userPassword
    val passwordVisible by viewModel::passwordVisible
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()

    var registeredId by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }

    val signUpLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                registeredId = data.getStringExtra(ID_KEY).orEmpty()
                registeredPassword = data.getStringExtra(PASSWORD_KEY).orEmpty()
            }
        }
    }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { message ->
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "닫기",
                duration = SnackbarDuration.Indefinite
            )

            if (result == SnackbarResult.ActionPerformed && message == context.getString(R.string.signin_success)) {
                navController.navigate(Routes.My.route){
                    popUpTo(Routes.SignIn.route){
                        inclusive = true
                    }
                }
            }

            viewModel.clearSnackbarMessage()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.Black)
                .padding(20.dp)
        ) {
            IDTextField(
                value = userId,
                onValueChange = viewModel::updateUserId,
                modifier = Modifier.fillMaxWidth(),
                placeholder = context.getString(R.string.signin_id)
            )
            Spacer(Modifier.height(5.dp))

            PasswordTextField(
                value = userPassword,
                onValueChange = viewModel::updateUserPassword,
                placeholder = stringResource(R.string.signin_password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onTrailingIconClick = {
                    viewModel.togglePasswordVisibility()
                }
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.performLogin(registeredId, registeredPassword)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Text(
                    text = stringResource(R.string.signin_button),
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.signin_find_id),
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        TODO("아이디 찾기 버튼")
                    }
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.signin_dash),
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Spacer(Modifier.width(10.dp))

                Text(
                    text = stringResource(R.string.signin_reset_password),
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        TODO("비밀번호 찾기 버튼")
                    }
                )
                Spacer(Modifier.width(10.dp))

                Text(
                    text = stringResource(R.string.signin_dash),
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Spacer(Modifier.width(10.dp))

                // 회원가입
                Text(
                    text = stringResource(R.string.signin_to_signup),
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUp.route)
                    }
                )
            }
        }
    }
}