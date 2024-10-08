package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
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

                TextField(
                    value = userId,
                    onValueChange = {userId = it},
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.signup_id),
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.DarkGray
                    )
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

                TextField(
                    value = userPassWord,
                    onValueChange = {userPassWord = it},
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.signup_password),
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.DarkGray
                    ),
                    visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Text(
                            text = "show",
                            color = Color.White,
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            }
                        )
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
                        // 조건에 맞을 경우에만 화면 전환
                        if (isAbleEmail(userId) && isAblePassword(userPassWord)){
                            Intent(context, SignInActivity::class.java).apply {
                                context.startActivity(this)
                            }
                        }else{
                            Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT).show()
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
    val pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}\$"
    return Regex(pattern).matches(password)
}