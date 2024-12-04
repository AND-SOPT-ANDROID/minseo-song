package org.sopt.and.presentation.ui.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.presentation.ui.component.InfoTextWithIcon
import org.sopt.and.presentation.ui.component.textField.IDTextField
import org.sopt.and.presentation.ui.component.textField.PasswordTextField
import org.sopt.and.presentation.ui.navigation.Routes

@Composable
fun SignUpScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()

    val userId by signUpViewModel.userId.collectAsStateWithLifecycle()
    val userPassWord by signUpViewModel.userPassWord.collectAsStateWithLifecycle()
    val userHobby by signUpViewModel.userHobby.collectAsStateWithLifecycle()
    val errorMessage by signUpViewModel.errorMessage.collectAsStateWithLifecycle()
    val successMessage by signUpViewModel.successMessage.collectAsStateWithLifecycle()
    val navigateToSignIn by signUpViewModel.navigateToSignIn.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (navigateToSignIn) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, successMessage ?: "회원가입 성공!", Toast.LENGTH_SHORT).show()
            signUpViewModel.clearNavigationFlag()
            navController.navigate(Routes.SignIn.route) {
                popUpTo(Routes.SignUp.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = stringResource(R.string.signup_text),
                    color = Color.Gray
                )
                Spacer(Modifier.height(20.dp))

                IDTextField(
                    value = userId,
                    onValueChange = { signUpViewModel.updateUserId(it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = context.getString(R.string.signup_id)
                )
                Spacer(Modifier.height(10.dp))

                InfoTextWithIcon(
                    text = stringResource(R.string.signup_id_explain)
                )
                Spacer(Modifier.height(20.dp))

                PasswordTextField(
                    value = userPassWord,
                    onValueChange = { signUpViewModel.updateUserPassword(it) },
                    placeholder = stringResource(R.string.signin_password)
                )
                Spacer(Modifier.height(10.dp))

                InfoTextWithIcon(
                    text = stringResource(R.string.signup_password_explain)
                )

                IDTextField(
                    value = userHobby,
                    onValueChange = { signUpViewModel.updateUserHobby(it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = context.getString(R.string.signup_hobby)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = {
                        signUpViewModel.signUpUser()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray.copy(alpha = 0.5f)
                    ),
                    shape = RectangleShape
                ) {
                    Text(
                        text = stringResource(R.string.signup_button),
                        color = Color.White
                    )
                }

                errorMessage?.let {
                    Spacer(Modifier.height(10.dp))
                    Text(text = it, color = Color.Red)
                }
            }
        }
    }
}