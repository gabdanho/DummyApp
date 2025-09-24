package com.example.authapp.presentation.screens.posts

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.UserPosts

data class UserPostsScreenUiState(
    val posts: UserPosts = UserPosts(),
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
)