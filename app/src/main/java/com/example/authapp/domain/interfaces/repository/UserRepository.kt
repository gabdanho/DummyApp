package com.example.authapp.domain.interfaces.repository

import com.example.authapp.domain.model.ApiResult
import com.example.authapp.domain.model.user.AuthRequest
import com.example.authapp.domain.model.user.Post
import com.example.authapp.domain.model.user.PostComments
import com.example.authapp.domain.model.user.User
import com.example.authapp.domain.model.user.UserList
import com.example.authapp.domain.model.user.UserLogin
import com.example.authapp.domain.model.user.UserPosts

/**
 * Репозиторий для работы с пользователями, постами и комментариями.
 *
 * Определяет контракты для получения и обработки данных из различных источников (например, сети или базы).
 * Все методы возвращают результат в виде [ApiResult], чтобы обрабатывать успешные ответы и ошибки единообразно.
 */
interface UserRepository {

    /**
     * Авторизует пользователя.
     *
     * @param authRequest данные для авторизации [AuthRequest].
     * @return [ApiResult] с результатом авторизации [UserLogin].
     */
    suspend fun authUser(authRequest: AuthRequest): ApiResult<UserLogin>

    /**
     * Получает данные о пользователе по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return [ApiResult] с [User].
     */
    suspend fun getCurrentUser(id: Int): ApiResult<User>

    /**
     * Получает посты пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return [ApiResult] с [UserPosts].
     */
    suspend fun getUserPostsByUserId(id: Int): ApiResult<UserPosts>

    /**
     * Получает пост по идентификатору.
     *
     * @param id идентификатор поста.
     * @return [ApiResult] с [Post].
     */
    suspend fun getCurrentPostById(id: Int): ApiResult<Post>

    /**
     * Получает список пользователей по поисковому запросу.
     *
     * @param searchText строка поиска.
     * @return [ApiResult] с [UserList].
     */
    suspend fun getPersonsBySearchText(searchText: String): ApiResult<UserList>

    /**
     * Получает комментарии к посту по его идентификатору.
     *
     * @param id идентификатор поста.
     * @return [ApiResult] с [PostComments].
     */
    suspend fun getCommentsByPostId(id: Int): ApiResult<PostComments>
}