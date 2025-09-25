package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.UserLogin
import com.example.authapp.domain.model.user.UserLogin as UserLoginDomain

/**
 * Преобразует [UserLoginDomain] в [UserLogin].
 */
fun UserLoginDomain.toPresentationLayer(): UserLogin {
    return UserLogin(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image,
        token
    )
}

/**
 * Преобразует [UserLogin] в [UserLoginDomain].
 */
fun UserLogin.toDomainLayer(): UserLoginDomain {
    return UserLoginDomain(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image,
        token
    )
}