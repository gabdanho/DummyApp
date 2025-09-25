package com.example.authapp.presentation.screens.posts

import com.example.authapp.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.user.Post
import com.example.authapp.presentation.model.user.UserPosts
import com.example.authapp.presentation.components.ErrorScreen
import com.example.authapp.presentation.components.LoadingScreen
import com.example.authapp.presentation.theme.defaultDimensions
import com.example.authapp.presentation.utils.showUiMessage

/**
 * Экран постов пользователя.
 *
 * @param id идентификатор пользователя
 * @param modifier модификатор компоновки
 * @param viewModel [UserPostsScreenViewModel] для управления состоянием
 */
@Composable
fun UserPostsScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: UserPostsScreenViewModel = hiltViewModel<UserPostsScreenViewModel>(),
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

    LaunchedEffect(id) {
        viewModel.getUserPosts(userId = id)
    }

    Scaffold(
        topBar = {
            PostScreenTopBar(
                onBackButtonClick = { viewModel.onBackButtonClick() },
                modifier = Modifier
                    .padding(defaultDimensions.small)
                    .fillMaxWidth()
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (uiState.loadingState) {
            is LoadingState.Success -> {
                PostsList(
                    userPosts = uiState.posts,
                    onPostClick = { viewModel.onPostClick(postId = it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is LoadingState.Loading -> {
                LoadingScreen()
            }

            is LoadingState.Error -> {
                uiState.loadingState?.let {
                    ErrorScreen(onUpdateScreen = { viewModel.getUserPosts(userId = id) }, loadingState = it)
                }
            }

            null -> {}
        }
    }
}

@Composable
private fun PostScreenTopBar(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
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
        Text(
            text = stringResource(id = R.string.text_posts),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(horizontal = defaultDimensions.small)
        )
    }
}

@Composable
private fun PostsList(
    userPosts: UserPosts,
    onPostClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (userPosts.posts.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            items(userPosts.posts) { post ->
                UserPost(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(defaultDimensions.small),
                    post = post,
                    onPostClick = onPostClick
                )
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.text_there_are_no_posts_yet))
        }
    }
}

@Composable
private fun UserPost(
    post: Post,
    onPostClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onPostClick(post.id) },
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(defaultDimensions.small)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = post.body,
                modifier = Modifier.padding(vertical = defaultDimensions.small)
            )
        }
    }
}