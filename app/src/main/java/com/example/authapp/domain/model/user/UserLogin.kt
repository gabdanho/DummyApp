package com.example.authapp.domain.model.user

/**
 * Результат авторизации пользователя.
 *
 * @property id идентификатор.
 * @property username логин.
 * @property email почта.
 * @property firstName имя.
 * @property lastName фамилия.
 * @property gender пол.
 * @property image аватар.
 * @property token JWT-токен.
 */
data class UserLogin(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val image: String = "",
    val token: String = "",
)