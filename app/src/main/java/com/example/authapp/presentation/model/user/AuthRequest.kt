package com.example.authapp.presentation.model.user

/**
 * Запрос авторизации.
 *
 * @property username имя пользователя.
 * @property password пароль пользователя.
 */
data class AuthRequest(
    val username: String = "",
    val password: String = "",
)
