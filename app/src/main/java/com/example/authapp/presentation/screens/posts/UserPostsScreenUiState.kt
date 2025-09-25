package com.example.authapp.presentation.screens.posts

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.UserPosts

/**
 * Состояние [UserPostsScreen].
 *
 * @param posts список постов пользователя
 * @param loadingState состояние загрузки
 * @param uiMessage сообщение для пользователя
 */
data class UserPostsScreenUiState(
    val posts: UserPosts = UserPosts(),
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
)