package com.example.authapp.network

import com.example.authapp.model.AuthRequest
import com.example.authapp.model.User
import com.example.authapp.model.Post
import com.example.authapp.model.PostComments
import com.example.authapp.model.UserList
import com.example.authapp.model.UserLogin
import com.example.authapp.model.UserPosts
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