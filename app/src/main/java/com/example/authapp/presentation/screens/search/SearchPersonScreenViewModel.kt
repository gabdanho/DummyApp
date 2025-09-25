package com.example.authapp.presentation.screens.search

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

@HiltViewModel
class SearchPersonScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchPersonScreenUiState())
    val uiState: StateFlow<SearchPersonScreenUiState> = _uiState.asStateFlow()

    fun onBackButtonClick() {
        viewModelScope.launch {
            navigator.navigatePopBackStack()
        }
    }

    fun onPromptValueChange(value: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    loadingState = LoadingState.Loading,
                    promptValue = value
                )
            }

            val state = _uiState.value
            when (val result =
                userRepository.getPersonsBySearchText(searchText = state.promptValue)) {
                is ApiResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Success,
                            users = result.data.toPresentationLayer()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = LoadingState.Error,
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_GET_USERS,
                                details = result.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun onUserClick(userId: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.UserDetailsScreen(id = userId)
            )
        }
    }

    fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }
}