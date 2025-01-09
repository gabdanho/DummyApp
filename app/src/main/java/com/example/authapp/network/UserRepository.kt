package com.example.authapp.network

import com.example.authapp.ui.model.AuthRequest
import com.example.authapp.ui.model.Post
import com.example.authapp.ui.model.PostComments
import com.example.authapp.ui.model.UserList
import com.example.authapp.ui.model.User
import com.example.authapp.ui.model.UserLogin
import com.example.authapp.ui.model.UserPosts

interface UserRepository {
    suspend fun authUser(authRequest: AuthRequest): UserLogin
    suspend fun getCurrentUser(id: Int): User
    suspend fun getUserPostsByUserId(id: Int): UserPosts
    suspend fun getCurrentPostById(id: Int): Post
    suspend fun getPersonsBySearchText(searchText: String): UserList
    suspend fun getCommentsByPostId(id: Int): PostComments
}

class NetworkUserRepository(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun authUser(authRequest: AuthRequest): UserLogin =
        userApiService.authUser(authRequest)

    override suspend fun getCurrentUser(id: Int): User =
        userApiService.getUser(id)

    override suspend fun getUserPostsByUserId(id: Int): UserPosts =
        userApiService.getPostsByUserId(id)

    override suspend fun getCurrentPostById(id: Int): Post =
        userApiService.getPost(id)

    override suspend fun getPersonsBySearchText(searchText: String): UserList =
        userApiService.getPersonsBySearchText(searchText)

    override suspend fun getCommentsByPostId(id: Int): PostComments =
        userApiService.getCommentsByPostId(id)
}