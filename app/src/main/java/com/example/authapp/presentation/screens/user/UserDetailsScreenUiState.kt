package com.example.authapp.presentation.screens.user

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.User

data class UserDetailsScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val user: User = User(),
)