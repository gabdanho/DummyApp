package com.example.authapp.presentation.screens.auth

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.authapp.R
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.utils.showUiMessage

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthScreenViewModel = hiltViewModel<AuthScreenViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let {
            context.showUiMessage(
                uiMessage = it,
                clearMessage = { viewModel.clearMessage() }
            )
        }
    }

    Scaffold(
        topBar = { TopAppBarAuth() },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (uiState.loadingState is LoadingState.Error) {
                ErrorText()
            }
            Spacer(modifier = Modifier.height(24.dp))
            AuthLogo()
            // Username TextField
            UsernameInput(
                username = uiState.usernameValue,
                updateUsername = { viewModel.usernameValueUpdate(value = it) },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 8.dp)
            )
            // Password TextField
            PasswordInput(
                passwordValue = uiState.passwordValue,
                isPasswordVisible = !uiState.isPasswordHidden,
                updatePasswordValue = { viewModel.passwordValueUpdate(value = it) },
                updatePasswordVisible = { viewModel.isPasswordHiddenUpdate() },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 8.dp)
            )
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 36.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarAuth(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Welcome")
        },
        modifier = modifier
    )
}

@Composable
private fun ErrorText(modifier: Modifier = Modifier) {
    Text(
        text = "Invalid username and/or password",
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}

@Composable
private fun AuthLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Auth Logo",
        modifier = modifier
    )
}

@Composable
private fun PasswordInput(
    passwordValue: String,
    isPasswordVisible: Boolean,
    updatePasswordValue: (String) -> Unit,
    updatePasswordVisible: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = passwordValue,
        onValueChange = { updatePasswordValue(it) },
        placeholder = { Text(text = "Password") },
        singleLine = true,
        visualTransformation = if (!isPasswordVisible)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (isPasswordVisible)
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
        modifier = modifier
    )
}

@Composable
private fun UsernameInput(
    username: String,
    updateUsername: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = username,
        onValueChange = { updateUsername(it) },
        placeholder = { Text(text = "Username") },
        singleLine = true,
        modifier = modifier
    )
}