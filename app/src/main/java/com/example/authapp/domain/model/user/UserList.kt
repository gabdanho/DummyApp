package com.example.authapp.domain.model.user

/**
 * Список пользователей.
 *
 * @property users список [User].
 * @property total общее количество пользователей.
 */
data class UserList(
    val users: List<User> = emptyList(),
    val total: Int = 0,
)