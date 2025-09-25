package com.example.authapp.presentation.screens.search

import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.model.user.UserList

/**
 * Состояние [SearchPersonScreen].
 *
 * @param loadingState состояние загрузки
 * @param uiMessage сообщение для пользователя
 * @param promptValue введённый поисковый запрос
 * @param users список найденных пользователей
 */
data class SearchPersonScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val promptValue: String = "",
    val users: UserList = UserList(),
)