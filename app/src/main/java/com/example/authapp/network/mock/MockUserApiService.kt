package com.example.authapp.network.mock

import com.example.authapp.ui.model.AuthRequest
import com.example.authapp.ui.model.User
import com.example.authapp.ui.model.Post
import com.example.authapp.ui.model.PostComments
import com.example.authapp.ui.model.UserList
import com.example.authapp.ui.model.UserLogin
import com.example.authapp.ui.model.UserPosts
import com.example.authapp.network.UserApiService

class MockUserApiService: UserApiService {
    override suspend fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun authUser(authRequest: AuthRequest): UserLogin {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsByUserId(id: Int): UserPosts {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(id: Int): Post {
        TODO("Not yet implemented")
    }

    override suspend fun getPersonsBySearchText(searchText: String): UserList {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentsByPostId(id: Int): PostComments {
        TODO("Not yet implemented")
    }
}