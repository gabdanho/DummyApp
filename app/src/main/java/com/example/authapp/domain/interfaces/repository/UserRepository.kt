package com.example.authapp.domain.interfaces.repository

import com.example.authapp.domain.model.ApiResult
import com.example.authapp.domain.model.user.AuthRequest
import com.example.authapp.domain.model.user.Post
import com.example.authapp.domain.model.user.PostComments
import com.example.authapp.domain.model.user.User
import com.example.authapp.domain.model.user.UserList
import com.example.authapp.domain.model.user.UserLogin
import com.example.authapp.domain.model.user.UserPosts

interface UserRepository {

    suspend fun authUser(authRequest: AuthRequest): ApiResult<UserLogin>

    suspend fun getCurrentUser(id: Int): ApiResult<User>

    suspend fun getUserPostsByUserId(id: Int): ApiResult<UserPosts>

    suspend fun getCurrentPostById(id: Int): ApiResult<Post>

    suspend fun getPersonsBySearchText(searchText: String): ApiResult<UserList>

    suspend fun getCommentsByPostId(id: Int): ApiResult<PostComments>
}