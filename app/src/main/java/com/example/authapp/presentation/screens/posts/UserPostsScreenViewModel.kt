package com.example.authapp.presentation.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.domain.interfaces.repository.UserRepository
import com.example.authapp.domain.model.ApiResult
import com.example.authapp.presentation.mapper.toPresentationLayer
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.StringResNamePresentation
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.navigation.Navigator
import com.example.authapp.presentation.navigation.model.AppGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для [UserPostsScreen].
 *
 * @param navigator навигация между экранами
 * @param userRepository репозиторий пользователей
 */
@HiltViewModel
class UserPostsScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserPostsScreenUiState())
    val uiState: StateFlow<UserPostsScreenUiState> = _uiState.asStateFlow()

    fun onBackButtonClick() {
        viewModelScope.launch {
            navigator.navigatePopBackStack()
        }
    }

    fun getUserPosts(userId: Int) {
        _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
        viewModelScope.launch {
            when (val result = userRepository.getUserPostsByUserId(id = userId)) {
                is ApiResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Success,
                            posts = result.data.toPresentationLayer()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Error,
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_GET_USER_POSTS,
                                details = result.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun onPostClick(postId: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.PostScreen(id = postId)
            )
        }
    }

    fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }
}