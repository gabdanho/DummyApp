package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.UserPosts
import com.example.authapp.domain.model.user.UserPosts as UserPostsDomain

/**
 * Преобразует [UserPostsDomain] в [UserPosts].
 */
fun UserPostsDomain.toDataLayer(): UserPosts {
    return UserPosts(
        posts = posts.map { it.toDataLayer() }
    )
}

/**
 * Преобразует [UserPosts] в [UserPostsDomain].
 */
fun UserPosts.toDomainLayer(): UserPostsDomain {
    return UserPostsDomain(
        posts = posts.map { it.toDomainLayer() }
    )
}