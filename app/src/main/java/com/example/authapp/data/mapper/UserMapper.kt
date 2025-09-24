package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.User
import com.example.authapp.domain.model.user.User as UserDomain

fun UserDomain.toDataLayer(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        age = age,
        gender = gender,
        birthDate = birthDate,
        image = image,
        university = university,
        department = department
    )
}

fun User.toDomainLayer(): UserDomain {
    return UserDomain(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        age = age,
        gender = gender,
        birthDate = birthDate,
        image = image,
        university = university,
        department = department
    )
}