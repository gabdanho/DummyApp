package com.example.authapp.data.remote.model.user

/**
 * Пользователь.
 *
 * @property id идентификатор.
 * @property firstName имя.
 * @property lastName фамилия.
 * @property username логин.
 * @property age возраст.
 * @property gender пол.
 * @property birthDate дата рождения.
 * @property image аватар.
 * @property university университет.
 * @property department факультет/кафедра.
 */
data class User(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val age: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val image: String = "",
    val university: String = "",
    val department: String = "",
)