package com.example.authapp.presentation.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.data.repository.impl.remote.UserRepository
import com.example.authapp.domain.model.user.AuthRequest
import com.example.authapp.presentation.model.AuthUiState
import com.example.authapp.presentation.model.CurrentPostUiState
import com.example.authapp.presentation.model.CurrentUserUiState
import com.example.authapp.presentation.model.PostCommentsUiState
import com.example.authapp.presentation.model.SearchPersonsUiState
import com.example.authapp.presentation.model.UserPostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

val TAG = "AuthViewModel"

data class ViewModelUiState(
    val searchText: String = ""
)

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ViewModelUiState())
    val uiState: StateFlow<ViewModelUiState> = _uiState.asStateFlow()

    var authUiState: AuthUiState by mutableStateOf(AuthUiState.Loading)
        private set
    var currentUserUiState: CurrentUserUiState by mutableStateOf(CurrentUserUiState.Loading)
        private set
    var userPostsUiState: UserPostsUiState by mutableStateOf(UserPostsUiState.Loading)
        private set
    var currentPostUiState: CurrentPostUiState by mutableStateOf(CurrentPostUiState.Loading)
        private set
    var searchPersonsUiState: SearchPersonsUiState by mutableStateOf(SearchPersonsUiState.Loading)
        private set
    var postCommentsUiState: PostCommentsUiState by mutableStateOf(PostCommentsUiState.Loading)

    fun authUser(authRequest: AuthRequest): LiveData<Boolean> {
        val resultLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            authUiState = AuthUiState.Loading
            try {
                authUiState = AuthUiState.Success(userRepository.authUser(authRequest))
                resultLiveData.postValue(true)
            } catch (e: IOException) {
                authUiState = AuthUiState.Error
                resultLiveData.postValue(false)
            } catch (e: HttpException) {
                authUiState = AuthUiState.Error
                resultLiveData.postValue(false)
            }
        }
        return resultLiveData
    }

    fun getCurrentUser(id: Int) {
        viewModelScope.launch {
            currentUserUiState = try {
                CurrentUserUiState.Success(userRepository.getCurrentUser(id))
            } catch (e: IOException) {
                CurrentUserUiState.Error
            } catch (e: HttpException) {
                CurrentUserUiState.Error
            }
        }
    }

    fun getUserPosts(id: Int) {
        viewModelScope.launch {
            userPostsUiState = try {
                UserPostsUiState.Success(userRepository.getUserPostsByUserId(id))
            } catch (e: IOException) {
                UserPostsUiState.Error
            } catch (e: HttpException) {
                UserPostsUiState.Error
            }
        }
    }

    fun getPost(id: Int) {
        viewModelScope.launch {
            currentPostUiState = try {
                CurrentPostUiState.Success(userRepository.getCurrentPostById(id))
            } catch (e: IOException) {
                CurrentPostUiState.Error
            } catch (e: HttpException) {
                CurrentPostUiState.Error
            }
        }
    }

    fun getPostComments(id: Int) {
        viewModelScope.launch {
            postCommentsUiState = try {
                PostCommentsUiState.Success(userRepository.getCommentsByPostId(id))
            } catch (e: IOException) {
                PostCommentsUiState.Error
            } catch (e: HttpException) {
                PostCommentsUiState.Error
            }
        }
    }

    private fun getUserListBySearchText() {
        viewModelScope.launch {
            searchPersonsUiState = try {
                SearchPersonsUiState.Success(
                    userRepository.getPersonsBySearchText(uiState.value.searchText)
                )
            } catch (e: IOException) {
                SearchPersonsUiState.Error
            } catch (e: HttpException) {
                SearchPersonsUiState.Error
            }
        }
        Log.e(TAG, searchPersonsUiState.toString())
    }

    fun updateSearchText(text: String) {
        _uiState.update { currentState ->
            currentState.copy(searchText = if (text == "") " " else text)
        }
        getUserListBySearchText()
    }

    fun resetSearchTextAndSearchPersonsUiState() {
        _uiState.update { currentState ->
            currentState.copy(searchText = " ")
        }
        searchPersonsUiState = SearchPersonsUiState.Loading
    }

    fun resetAuthUser() {
        authUiState = AuthUiState.Loading
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as AuthApplication)
//                val userRepository = application.container.userRepository
//                AuthViewModel(userRepository)
//            }
//        }
//    }
}