package com.example.authapp.data.mapper

import com.example.authapp.data.remote.model.user.UserPosts
import com.example.authapp.domain.model.user.UserPosts as UserPostsDomain

fun UserPostsDomain.toDataLayer(): UserPosts {
    return UserPosts(
        posts = posts.map { it.toDataLayer() }
    )
}

fun UserPosts.toDomainLayer(): UserPostsDomain {
    return UserPostsDomain(
        posts = posts.map { it.toDomainLayer() }
    )
}