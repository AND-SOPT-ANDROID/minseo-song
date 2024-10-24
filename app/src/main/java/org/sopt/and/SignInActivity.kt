package org.sopt.and

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.component.IDTextField
import org.sopt.and.component.PasswordTextField
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


const val ID_KEY = "id"
const val PASSWORD_KEY = "password"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var userId by remember { mutableStateOf("") }
    var userPassWord by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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
    val coroutineScope = rememberCoroutineScope()

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
                onValueChange = {userId = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = context.getString(R.string.signin_id)
            )
            Spacer(Modifier.height(5.dp))

            PasswordTextField(
                value = userPassWord,
                onValueChange = { userPassWord = it },
                placeholder = stringResource(R.string.signin_password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onTrailingIconClick = {
                    passwordVisible = !passwordVisible
                }
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    val loginSuccess = performLogin(userId, userPassWord, registeredId, registeredPassword)
                    val snackbarMessage = if (loginSuccess) {
                        context.getString(R.string.signin_success)
                    } else {
                        context.getString(R.string.signin_fail)
                    }

                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            actionLabel = "닫기",
                            duration = SnackbarDuration.Indefinite
                        )

                        if (result == SnackbarResult.ActionPerformed && loginSuccess) {
                            Intent(context, MyActivity::class.java).apply {
                                putExtra(ID_KEY, userId)
                                context.startActivity(this)
                            }
                        }
                    }
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
                        val intent = Intent(context, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)
                    }
                )
            }
        }
    }
}

fun performLogin(userId: String, userPassword: String, registeredId: String, registeredPassword: String): Boolean {
    return userId == registeredId && userPassword == registeredPassword && userId.isNotBlank() && userPassword.isNotBlank()
}