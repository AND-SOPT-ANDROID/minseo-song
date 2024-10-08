package org.sopt.and

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var userId by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember{
        mutableStateOf(false)
    }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var registeredId by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }

    val signUpLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                registeredId = data.getStringExtra("userId") ?: ""
                registeredPassword = data.getStringExtra("userPassWord") ?: ""
            }
        }
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
            ){
                TextField(
                    value = userId,
                    onValueChange = {userId = it},
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.signin_id),
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.DarkGray
                    )
                )
                Spacer(Modifier.height(5.dp))

                TextField(
                    value = userPassWord,
                    onValueChange = {userPassWord = it},
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.signin_password),
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
                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {
                        // 조건에 맞을 경우에만 화면 전환
                        Log.d("로그인","${userId}, ${userPassWord}, ${registeredId}, ${registeredPassword}")
                        if (userId == registeredId && userPassWord == registeredPassword && (userId != "") && (userPassWord != "")){
                            snackbarMessage = "로그인 성공"
                        }else{
                            snackbarMessage = "로그인 실패"
                        }
                        snackbarVisible = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Text(
                        text = stringResource(R.string.signin_button),
                        color = Color.White
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
                            // 아이디 찾기 버튼
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
                            // 비밀번호 재설정 버튼
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
                            // 회원가입 버튼
                            val intent = Intent(context, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        }
                    )
                }

                if (snackbarVisible){
                    Snackbar(
                        action = {
                            TextButton(onClick = {
                                snackbarVisible = false
                                val intent = Intent(context, MyActivity::class.java)
                                intent.putExtra("userId", userId)
                                context.startActivity(intent)
                            }) {
                                Text("닫기", color = Color.White)
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ){
                        Text(text = snackbarMessage, color = Color.White)
                    }
                }
            }
        }
    }
}