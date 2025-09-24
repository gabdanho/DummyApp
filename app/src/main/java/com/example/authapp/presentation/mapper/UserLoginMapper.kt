package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.UserLogin
import com.example.authapp.domain.model.user.UserLogin as UserLoginDomain

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