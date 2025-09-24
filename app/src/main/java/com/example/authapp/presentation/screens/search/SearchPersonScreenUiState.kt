package com.example.authapp.presentation.screens.search

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.UserList

data class SearchPersonScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val promptValue: String = "",
    val users: UserList = UserList(),
)