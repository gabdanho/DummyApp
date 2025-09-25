package com.example.authapp.presentation.screens.search

import com.example.authapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.user.User
import com.example.authapp.presentation.theme.defaultDimensions
import com.example.authapp.presentation.utils.showUiMessage

/**
 * Экран поиска пользователей.
 *
 * @param authUserId идентификатор авторизованного пользователя
 * @param modifier модификатор компоновки
 * @param viewModel [SearchPersonScreenViewModel] для управления состоянием
 */
@Composable
fun SearchPersonScreen(
    authUserId: Int,
    modifier: Modifier = Modifier,
    viewModel: SearchPersonScreenViewModel = hiltViewModel<SearchPersonScreenViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let {
            context.showUiMessage(
                uiMessage = it,
                clearMessage = { viewModel.clearMessage() }
            )
        }
    }

    Scaffold(
        topBar = {
            SearchAppBar(
                searchTextValue = uiState.promptValue,
                onBackButtonClick = { viewModel.onBackButtonClick() },
                updateSearchText = { viewModel.onPromptValueChange(value = it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(defaultDimensions.small)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (uiState.loadingState is LoadingState.Loading) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(defaultDimensions.small)
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(defaultDimensions.small)
            ) {
                items(uiState.users.users) { user ->
                    if (user.id != authUserId) {
                        PersonItem(
                            user = user,
                            onCurrentUserClick = { viewModel.onUserClick(userId = user.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = defaultDimensions.small)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchAppBar(
    searchTextValue: String,
    onBackButtonClick: () -> Unit,
    updateSearchText: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = onBackButtonClick,
            modifier = Modifier.size(defaultDimensions.searchIconSize),
            contentPadding = PaddingValues(defaultDimensions.none)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.content_back_button)
            )
        }
        Spacer(modifier = Modifier.width(defaultDimensions.medium))
        OutlinedTextField(
            value = searchTextValue,
            onValueChange = { updateSearchText(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.content_search_icon)
                )
            },
            modifier = Modifier
                .height(defaultDimensions.searchIconSize)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun PersonItem(
    user: User,
    onCurrentUserClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable { onCurrentUserClick(user.id) }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.image)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.content_user_image),
            modifier = Modifier.size(defaultDimensions.userImageInPersonItem)
        )
        Column(modifier = Modifier.padding(start = defaultDimensions.small)) {
            Text(
                text = user.firstName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = user.lastName,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}