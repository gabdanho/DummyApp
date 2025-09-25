package com.example.authapp.presentation.screens.post

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.Post
import com.example.authapp.presentation.model.user.PostComments

/**
 * Состояние [SelectedPostScreen].
 *
 * @param post выбранный пост
 * @param comments комментарии к посту
 * @param loadingState состояние загрузки
 * @param uiMessage сообщение для пользователя
 */
data class SelectedPostScreenUiState(
    val post: Post = Post(),
    val comments: PostComments = PostComments(),
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
)