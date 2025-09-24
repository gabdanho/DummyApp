package com.example.authapp.presentation.screens.post

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.Post

data class SelectedPostScreenUiState(
    val post: Post = Post(),
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
)