package com.example.authapp.data.remote.api

import com.example.authapp.data.remote.model.user.AuthRequest
import com.example.authapp.data.remote.model.user.Post
import com.example.authapp.data.remote.model.user.PostComments
import com.example.authapp.data.remote.model.user.User
import com.example.authapp.data.remote.model.user.UserList
import com.example.authapp.data.remote.model.user.UserLogin
import com.example.authapp.data.remote.model.user.UserPosts
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @POST("auth/login")
    suspend fun authUser(@Body authRequest: AuthRequest): UserLogin

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @GET("posts/user/{id}")
    suspend fun getPostsByUserId(@Path("id") id: Int): UserPosts

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @GET("users/search")
    suspend fun getPersonsBySearchText(@Query("q") searchText: String): UserList

    @GET("comments/post/{id}")
    suspend fun getCommentsByPostId(@Path("id") id: Int): PostComments
}