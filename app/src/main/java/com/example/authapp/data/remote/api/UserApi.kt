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

/**
 * Retrofit API сервис для работы с пользователями, постами и комментариями.
 */
interface UserApiService {

    /**
     * Авторизует пользователя.
     *
     * @param authRequest [AuthRequest] данные для авторизации.
     * @return [UserLogin] результат авторизации с токеном.
     */
    @POST("auth/login")
    suspend fun authUser(@Body authRequest: AuthRequest): UserLogin

    /**
     * Получает данные о пользователе по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return [User] объект пользователя.
     */
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    /**
     * Получает список постов пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return [UserPosts] список постов пользователя.
     */
    @GET("posts/user/{id}")
    suspend fun getPostsByUserId(@Path("id") id: Int): UserPosts

    /**
     * Получает пост по его идентификатору.
     *
     * @param id идентификатор поста.
     * @return [Post] объект поста.
     */
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    /**
     * Ищет пользователей по тексту.
     *
     * @param searchText поисковая строка.
     * @return [UserList] список пользователей.
     */
    @GET("users/search")
    suspend fun getPersonsBySearchText(@Query("q") searchText: String): UserList

    /**
     * Получает комментарии к посту по его идентификатору.
     *
     * @param id идентификатор поста.
     * @return [PostComments] список комментариев.
     */
    @GET("comments/post/{id}")
    suspend fun getCommentsByPostId(@Path("id") id: Int): PostComments
}