package com.example.authapp.presentation.screens.auth

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage

/**
 * Состояние [AuthScreen].
 *
 * @param usernameValue введённый логин
 * @param passwordValue введённый пароль
 * @param isPasswordHidden скрыт ли пароль
 * @param uiMessage сообщение для пользователя
 * @param loadingState состояние загрузки
 */
data class AuthScreenUiState(
    val usernameValue: String = "",
    val passwordValue: String = "",
    val isPasswordHidden: Boolean = true,
    val uiMessage: UiMessage? = null,
    val loadingState: LoadingState? = null,
)