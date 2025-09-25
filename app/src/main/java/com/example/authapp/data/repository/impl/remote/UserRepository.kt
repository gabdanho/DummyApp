package com.example.authapp.data.repository.impl.remote

import com.example.authapp.data.mapper.toDataLayer
import com.example.authapp.data.mapper.toDomainLayer
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

/**
 * Реализация [UserRepository], работающая через [UserApiService].
 */
class NetworkUserRepository @Inject constructor(
    private val userApiService: UserApiService,
) : UserRepository {

    /**
     * Авторизует пользователя.
     *
     * @param authRequest [AuthRequest] данные для авторизации.
     * @return [ApiResult] с [UserLogin].
     */
    override suspend fun authUser(authRequest: AuthRequest): ApiResult<UserLogin> {
        return safeApiCall {
            val mappedAuthRequest = authRequest.toDataLayer()

            userApiService.authUser(mappedAuthRequest).toDomainLayer()
        }
    }

    /**
     * Получает данные о текущем пользователе.
     *
     * @param id идентификатор пользователя.
     * @return [ApiResult] с [User].
     */
    override suspend fun getCurrentUser(id: Int): ApiResult<User> {
        return safeApiCall {
            userApiService.getUser(id).toDomainLayer()
        }
    }

    /**
     * Получает посты пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return [ApiResult] с [UserPosts].
     */
    override suspend fun getUserPostsByUserId(id: Int): ApiResult<UserPosts> {
        return safeApiCall {
            userApiService.getPostsByUserId(id).toDomainLayer()
        }
    }

    /**
     * Получает пост по идентификатору.
     *
     * @param id идентификатор поста.
     * @return [ApiResult] с [Post].
     */
    override suspend fun getCurrentPostById(id: Int): ApiResult<Post> {
        return safeApiCall {
            userApiService.getPost(id).toDomainLayer()
        }
    }

    /**
     * Получает пользователей по поисковому запросу.
     *
     * @param searchText поисковая строка.
     * @return [ApiResult] с [UserList].
     */
    override suspend fun getPersonsBySearchText(searchText: String): ApiResult<UserList> {
        return safeApiCall {
            userApiService.getPersonsBySearchText(searchText).toDomainLayer()
        }
    }

    /**
     * Получает комментарии к посту.
     *
     * @param id идентификатор поста.
     * @return [ApiResult] с [PostComments].
     */
    override suspend fun getCommentsByPostId(id: Int): ApiResult<PostComments> {
        return safeApiCall {
            userApiService.getCommentsByPostId(id).toDomainLayer()
        }
    }
}