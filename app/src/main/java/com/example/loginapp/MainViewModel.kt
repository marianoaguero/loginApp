package com.example.loginapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _loginState = mutableStateOf(LoginState())
    val loginState: LoginState = _loginState.value

    private var username: String = ""
    private var password: String = ""

    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun login(success: Boolean = true) {
        viewModelScope.launch {
            if (success) {
                loginUser()
            } else {
                loginUserFails()
            }
        }
    }

    private suspend fun loginUser() {
        delay(1000)
        _loginState.value = LoginState(isSuccess = true)
    }

    private suspend fun loginUserFails() {
        delay(1000)
        _loginState.value = LoginState(isSuccess = false, errorMessage = "Password is incorrect")
    }
}

data class LoginState(
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
)