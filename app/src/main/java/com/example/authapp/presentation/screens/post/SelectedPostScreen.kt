package com.example.authapp.presentation.screens.post

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
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.user.Post
import com.example.authapp.presentation.model.user.PostComments
import com.example.authapp.presentation.screens.proccesing.ErrorScreen
import com.example.authapp.presentation.screens.proccesing.LoadingScreen
import com.example.authapp.presentation.utils.showUiMessage

@Composable
fun SelectedPostScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: SelectedPostScreenViewModel = hiltViewModel<SelectedPostScreenViewModel>(),
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
        viewModel.getPost(id = id)
    }

    when (uiState.loadingState) {
        is LoadingState.Success -> {
            SelectedPost(
                post = uiState.post,
                postComments = uiState.comments,
                onBackButtonClick = { viewModel.onBackButtonClick() },
                modifier = modifier.padding(8.dp)
            )
        }

        is LoadingState.Error -> {
            ErrorScreen()
        }

        is LoadingState.Loading -> {
            LoadingScreen()
        }

        null -> {}
    }
}

@Composable
private fun SelectedPost(
    post: Post,
    postComments: PostComments,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            PostTopBar(
                postTitle = post.title,
                onBackButtonClick = onBackButtonClick,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            PostBody(
                post = post,
                modifier = Modifier.padding(8.dp)
            )
            PostComments(postComments = postComments)
        }
    }
}

@Composable
private fun PostTopBar(
    postTitle: String,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = onBackButtonClick,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Button"
            )
        }
        Text(
            text = postTitle,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
private fun PostBody(
    post: Post,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = post.body,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "Tags: ",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            post.tags.forEach {
                Text(
                    text = "$it | ",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
private fun PostComments(
    postComments: PostComments,
    modifier: Modifier = Modifier,
) {
    if (postComments.comments.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 8.dp),
                        thickness = DividerDefaults.Thickness,
                        color = Color.Black
                    )
                    Text(
                        text = "Comments",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 8.dp),
                        thickness = DividerDefaults.Thickness,
                        color = Color.Black
                    )
                }
            }
            items(postComments.comments) { comment ->
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = comment.user.username,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = comment.body)
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("There are no comments yet")
        }
    }
}