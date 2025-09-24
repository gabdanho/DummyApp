package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.UserList
import com.example.authapp.domain.model.user.UserList as UserListDomain

fun UserListDomain.toPresentationLayer(): UserList {
    return UserList(
        users = users.map { it.toPresentationLayer() },
        total = total
    )
}

fun UserList.toDomainLayer(): UserListDomain {
    return UserListDomain(
        users = users.map { it.toDomainLayer() },
        total = total
    )
}