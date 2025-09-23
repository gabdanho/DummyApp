package com.example.authapp.data.repository.impl.remote

import com.example.authapp.data.remote.api.UserApiService
import com.example.authapp.data.remote.model.safeApiCall
import com.example.authapp.domain.interfaces.repository.UserRepository
import com.example.authapp.domain.model.ApiResult
import com.example.authapp.domain.model.user.AuthRequest
import com.example.authapp.domain.model.user.Post
import com.example.authapp.domain.model.user.PostComments
import com.example.authapp.domain.model.user.User
import com.example.authapp.domain.model.user.UserList
import com.example.authapp.domain.model.user.UserLogin
import com.example.authapp.domain.model.user.UserPosts
import javax.inject.Inject

class NetworkUserRepository @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun authUser(authRequest: AuthRequest): ApiResult<UserLogin> {
        return safeApiCall {
            userApiService.authUser(authRequest)
        }
    }

    override suspend fun getCurrentUser(id: Int): ApiResult<User> {
        return safeApiCall {
            userApiService.getUser(id)
        }
    }

    override suspend fun getUserPostsByUserId(id: Int): ApiResult<UserPosts> {
        return safeApiCall {
            userApiService.getPostsByUserId(id)
        }
    }


    override suspend fun getCurrentPostById(id: Int): ApiResult<Post> {
        return safeApiCall {
            userApiService.getPost(id)
        }
    }

    override suspend fun getPersonsBySearchText(searchText: String): ApiResult<UserList> {
        return safeApiCall {
            userApiService.getPersonsBySearchText(searchText)
        }
    }

    override suspend fun getCommentsByPostId(id: Int): ApiResult<PostComments> {
        return safeApiCall {
            userApiService.getCommentsByPostId(id)
        }
    }
}