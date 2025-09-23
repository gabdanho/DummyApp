package com.example.authapp.presentation.model

import com.example.authapp.domain.model.user.UserLogin

sealed class AuthUiState {
    object Loading : AuthUiState()
    object Error : AuthUiState()
    data class Success(val user: UserLogin) : AuthUiState()
}

sealed class CurrentUserUiState {
    object Loading : CurrentUserUiState()
    object Error : CurrentUserUiState()
    data class Success(val currentUser: User) : CurrentUserUiState()
}

sealed class UserPostsUiState {
    object Loading: UserPostsUiState()
    object Error: UserPostsUiState()
    data class Success(val userPosts: UserPosts) : UserPostsUiState()
}

sealed class CurrentPostUiState {
    object Loading: CurrentPostUiState()
    object Error: CurrentPostUiState()
    data class Success(val post: Post) : CurrentPostUiState()
}

sealed class SearchPersonsUiState {
    object Loading: SearchPersonsUiState()
    object Error: SearchPersonsUiState()
    data class Success(val userList: UserList) : SearchPersonsUiState()
}

sealed class PostCommentsUiState {
    object Loading: PostCommentsUiState()
    object Error: PostCommentsUiState()
    data class Success(val postComments: PostComments) : PostCommentsUiState()
}