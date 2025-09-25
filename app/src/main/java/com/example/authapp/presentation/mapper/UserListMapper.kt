package com.example.authapp.presentation.mapper

import com.example.authapp.presentation.model.user.UserList
import com.example.authapp.domain.model.user.UserList as UserListDomain

/**
 * Преобразует [UserListDomain] в [UserList].
 */
fun UserListDomain.toPresentationLayer(): UserList {
    return UserList(
        users = users.map { it.toPresentationLayer() },
        total = total
    )
}

/**
 * Преобразует [UserList] в [UserListDomain].
 */
fun UserList.toDomainLayer(): UserListDomain {
    return UserListDomain(
        users = users.map { it.toDomainLayer() },
        total = total
    )
}