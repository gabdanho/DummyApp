package com.example.authapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.authapp.R
import com.example.authapp.ui.model.AuthRequest

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    isAuthError: Boolean?,
    password: String,
    username: String,
    passwordVisible: Boolean,
    updatePassword: (String) -> Unit,
    updateAuthError: (Boolean) -> Unit,
    updatePasswordVisible: () -> Unit,
    updateUsername: (String) -> Unit,
    authUser: (AuthRequest) -> LiveData<Boolean>,
    onLoginClick: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBarAuth() },
        content = { contentPadding ->
            AuthBody (
                modifier = Modifier.padding(contentPadding),
                authUser = authUser,
                isAuthError = isAuthError,
                password = password,
                username = username,
                passwordVisible = passwordVisible,
                onLoginClick = onLoginClick,
                updatePassword = updatePassword,
                updateAuthError = updateAuthError,
                updatePasswordVisible = updatePasswordVisible,
                updateUsername = updateUsername
            )
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAuth(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Welcome")
        },
        modifier = modifier
    )
}

@Composable
fun AuthBody(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    isAuthError: Boolean?,
    passwordVisible: Boolean,
    authUser: (AuthRequest) -> LiveData<Boolean>,
    updateUsername: (String) -> Unit,
    updatePassword: (String) -> Unit,
    onLoginClick: () -> Unit,
    updatePasswordVisible: () -> Unit,
    updateAuthError: (Boolean) -> Unit
) {
    val authResult by authUser(AuthRequest(username, password)).observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        ErrorText(isAuthError)
        Spacer(modifier = Modifier.height(24.dp))
        AuthLogo()
        // Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { updateUsername(it) },
            placeholder = { Text(text = "Username") },
            singleLine = true,
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 8.dp)
        )
        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { updatePassword(it) },
            placeholder = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = if (!passwordVisible)
                PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (passwordVisible)
                    R.drawable.show_password
                else R.drawable.hide_password
                IconButton(onClick = { updatePasswordVisible() }) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "Show Password",
                        modifier = Modifier.size(25.dp)
                    )
                }
            },
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 8.dp)
        )
        // Login Button
        Button(
            onClick = {
                if (authResult == true) {
                    onLoginClick()
                }
                else {
                    updateAuthError(authResult != true)
                }
            },
            modifier = Modifier
                .width(300.dp)
                .padding(top = 36.dp)
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun ErrorText(isError: Boolean?) {
    if (isError == true) {
        Text(
            text = "Invalid username and/or password",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun AuthLogo() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Auth Logo"
    )
}

//@Preview
//@Composable
//fun AuthScreenPreview() {
//    Scaffold(
//        topBar = { TopAppBarAuth() },
//        content = { contentPadding ->
//            AuthBody (
//                uiState = authorizationViewModel.uiState.value,
//                onUsernameChanged = authorizationViewModel::updateUsername,
//                onPasswordChanged = authorizationViewModel::updatePassword,
//                passwordVisibleChanged = authorizationViewModel::updatePasswordVisible,
//                checkAuthResult = authorizationViewModel::checkAuthResult,
//                onLoginClick = onLoginClick,
//                contentPadding = contentPadding
//            )
//        }
//    )
//}