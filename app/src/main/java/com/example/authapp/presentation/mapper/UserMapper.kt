package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.User
import com.example.authapp.domain.model.user.User as UserDomain

/**
 * Преобразует [UserDomain] в [User].
 */
fun UserDomain.toPresentationLayer(): User {
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

/**
 * Преобразует [User] в [UserDomain].
 */
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