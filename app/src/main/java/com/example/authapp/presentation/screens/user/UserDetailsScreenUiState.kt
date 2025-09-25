package com.example.authapp.presentation.screens.user

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.User

/**
 * Состояние [UserDetailsScreen].
 *
 * @param loadingState состояние загрузки
 * @param uiMessage сообщение для пользователя
 * @param user данные пользователя
 */
data class UserDetailsScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val user: User = User(),
)