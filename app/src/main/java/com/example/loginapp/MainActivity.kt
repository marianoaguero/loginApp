package com.example.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginapp.ui.theme.LoginAppTheme

class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    containerColor = Color.White
                ) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Login(
                            modifier = Modifier.padding(16.dp),
                            onUsernameChange = { mainViewModel.setUsername(it) },
                            onPasswordChange = { mainViewModel.setPassword(it) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LoginButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            onClick = { mainViewModel.login() },
                            text = stringResource(R.string.button_login)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LoginButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            onClick = { mainViewModel.login(false) },
                            text = stringResource(R.string.button_login_fails)
                        )
                        if (mainViewModel.loginState.isLoading) {
                            //TODO: show loading
                        } else if (mainViewModel.loginState.isSuccess){
                            Text(text = stringResource(R.string.login_success_msg))
                        } else {
                            Text(text = mainViewModel.loginState.errorMessage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier,
          onUsernameChange: (String) -> Unit = {},
          onPasswordChange: (String) -> Unit = {},
          ) {
    Column(modifier = modifier.wrapContentHeight()) {
        TextInputField(
            modifier = Modifier.padding(8.dp),
            value = "",
            onValueChange = { onUsernameChange },
            hint = stringResource(R.string.username_text_hint),
            title = stringResource(R.string.username_text_title),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInputField(
            modifier = Modifier.padding(8.dp),
            value = "",
            onValueChange = { onPasswordChange },
            hint = stringResource(R.string.password_text_hint),
            title = stringResource(R.string.password_text_title),
        )
    }
}

@Composable
fun TextInputField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    title: String
) {
    Text(modifier = Modifier.padding(start = 4.dp, bottom = 4.dp), text = title)
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint)
        },
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "Login",
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginAppTheme {
        Login()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    LoginAppTheme {
        LoginButton( onClick = {}, text = "Login")
    }
}