package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.UserLogin
import com.example.authapp.domain.model.user.UserLogin as UserLoginDomain

/**
 * Преобразует [UserLoginDomain] в [UserLogin].
 */
fun UserLoginDomain.toDataLayer(): UserLogin {
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