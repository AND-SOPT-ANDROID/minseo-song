package org.sopt.and.ui.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.component.IDTextField
import org.sopt.and.component.PasswordTextField
import org.sopt.and.ui.signin.ID_KEY
import org.sopt.and.ui.signin.PASSWORD_KEY
import org.sopt.and.ui.theme.ANDANDROIDTheme

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 20
val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{$PASSWORD_MIN_LENGTH,$PASSWORD_MAX_LENGTH}\$")

@Composable
fun SignUpScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var userId by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember{
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.TopStart)
            ){
                Text(
                    text = stringResource(R.string.signup_text),
                    color = Color.Gray
                )
                Spacer(Modifier.height(20.dp))

                IDTextField(
                    value = userId,
                    onValueChange = {userId = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = context.getString(R.string.signup_id)
                )
                Spacer(Modifier.height(10.dp))

                Row {
                    Image(
                        painter = painterResource(R.drawable.baseline_info_outline_24),
                        contentDescription = ""
                    )
                    Spacer(Modifier.width(5.dp))

                    Text(
                        text = stringResource(R.string.signup_id_explain),
                        color = Color.Gray
                    )
                }
                Spacer(Modifier.height(20.dp))

                PasswordTextField(
                    value = userPassWord,
                    onValueChange = { userPassWord = it },
                    placeholder = stringResource(R.string.signin_password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = {
                        passwordVisible = !passwordVisible
                    }
                )
                Spacer(Modifier.height(10.dp))

                Row {
                    Image(
                        painter = painterResource(R.drawable.baseline_info_outline_24),
                        contentDescription = ""
                    )
                    Spacer(Modifier.width(5.dp))

                    Text(
                        text = stringResource(R.string.signup_password_explain),
                        color = Color.Gray
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ){
                Button(
                    onClick = {
                        if (isAbleEmail(userId) && isAblePassword(userPassWord)){
                            navController.popBackStack()
                            Toast.makeText(context, (R.string.signup_success),Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, (R.string.signup_fail),Toast.LENGTH_SHORT).show()
                        }
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
            }
        }
    }
}

fun isAbleEmail(email: String): Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isAblePassword(password: String): Boolean{
    return PASSWORD_REGEX.matches(password)
}