package com.example.authapp.presentation.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.domain.interfaces.repository.UserRepository
import com.example.authapp.domain.model.ApiResult
import com.example.authapp.presentation.mapper.toPresentationLayer
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.StringResNamePresentation
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedPostScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SelectedPostScreenUiState())
    val uiState: StateFlow<SelectedPostScreenUiState> = _uiState.asStateFlow()

    fun onBackButtonClick() {
        viewModelScope.launch {
            navigator.navigatePopBackStack()
        }
    }

    fun getPost(id: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
            when (val result = userRepository.getCurrentPostById(id = id)) {
                is ApiResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Success,
                            post = result.data.toPresentationLayer()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Error,
                            uiMessage = UiMessage(
                                message = StringResNamePresentation.ERROR_GET_POST,
                                details = result.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }
}