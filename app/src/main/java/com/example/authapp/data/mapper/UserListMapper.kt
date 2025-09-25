package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.UserList
import com.example.authapp.domain.model.user.UserList as UserListDomain

/**
 * Преобразует [UserListDomain] в [UserList].
 */
fun UserListDomain.toDataLayer(): UserList {
    return UserList(
        users = users.map { it.toDataLayer() },
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