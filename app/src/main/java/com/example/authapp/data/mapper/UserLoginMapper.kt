package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.UserLogin
import com.example.authapp.domain.model.user.UserLogin as UserLoginDomain

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