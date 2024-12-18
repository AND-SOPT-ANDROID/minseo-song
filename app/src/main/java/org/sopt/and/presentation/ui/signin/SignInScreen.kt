package org.sopt.and.presentation.ui.signin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import org.sopt.and.R
import org.sopt.and.presentation.ui.component.textField.IDTextField
import org.sopt.and.presentation.ui.component.textField.PasswordTextField
import org.sopt.and.presentation.ui.navigation.Routes

@Composable
fun SignInScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val signInViewModel: SignInViewModel = hiltViewModel()

    val state by signInViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    signInViewModel.initializePreferences(context)
    val actionLabel = stringResource(R.string.signin_snackbar)

    LaunchedEffect(Unit) {
        signInViewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is SignInSideEffect.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = sideEffect.message,
                        actionLabel = actionLabel,
                        duration = SnackbarDuration.Indefinite

                    )
                    if (result == SnackbarResult.ActionPerformed){
                        signInViewModel.setEvent(SignInEvent.SignInClicked)
                        navController.navigate(Routes.My.route) {
                            popUpTo(Routes.SignIn.route) { inclusive = true }
                        }
                    }
                }
                is SignInSideEffect.NavigateToMyScreen -> {
                    navController.navigate(Routes.My.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                }
            }
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
                value = state.userId,
                onValueChange = { signInViewModel.setEvent(SignInEvent.UserIdChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = context.getString(R.string.signin_id)
            )
            Spacer(Modifier.height(5.dp))

            PasswordTextField(
                value = state.userPassWord,
                onValueChange = { signInViewModel.setEvent(SignInEvent.UserPasswordChanged(it)) },
                placeholder = stringResource(R.string.signin_password)
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    signInViewModel.setEvent(SignInEvent.SignInClicked)
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