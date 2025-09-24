package com.example.authapp.presentation.screens.auth

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage

data class AuthScreenUiState(
    val usernameValue: String = "",
    val passwordValue: String = "",
    val isPasswordHidden: Boolean = true,
    val uiMessage: UiMessage? = null,
    val loadingState: LoadingState? = null,
)