package com.example.authapp.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authapp.model.Comment
import com.example.authapp.model.CurrentPostUiState
import com.example.authapp.model.Post
import com.example.authapp.model.PostCommentsUiState
import com.example.authapp.model.fake.FakeDataClass

@Composable
fun SelectedPostScreen(
    postCommentsUiState: PostCommentsUiState,
    currentPostUiState: CurrentPostUiState,
    onBackButtonClick: () -> Unit
) {
    val postComments = if (postCommentsUiState is PostCommentsUiState.Success)
        postCommentsUiState.postComments.comments
    else
        emptyList()

    when(currentPostUiState) {
        is CurrentPostUiState.Success -> {
            SelectedPost(
                post = currentPostUiState.post,
                postComments = postComments,
                onBackButtonClick = onBackButtonClick
            )
        }
        is CurrentPostUiState.Error -> {
            ErrorScreen()
        }
        is CurrentPostUiState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun SelectedPost(
    post: Post,
    postComments: List<Comment>,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            PostTopBar(
                postTitle = post.title,
                onBackButtonClick = onBackButtonClick
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(8.dp)) {
            PostBody(
                innerPadding = innerPadding,
                post = post
            )
            PostComments(postComments)
        }
    }
}

@Composable
fun PostTopBar(
    postTitle: String,
    onBackButtonClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
    ) {
        OutlinedButton(
            onClick = onBackButtonClick,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
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
fun PostBody(
    post: Post,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(innerPadding)
    ) {
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
fun PostComments(postComments: List<Comment>) {
    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.Black)
    Text(
        text = "Comments",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Divider(modifier = Modifier.padding(bottom = 8.dp), color = Color.Black)
    if (postComments.isNotEmpty()) {
        LazyColumn {
            items(postComments) { comment ->
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = comment.user.username,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = comment.body)
                }
            }
        }
    }
    else {
        Text("There are no comments yet")
    }
}

@Preview
@Composable
fun SelectedPostPreview() {
    SelectedPost(
        postComments = FakeDataClass.commentsPost.comments,
        post = FakeDataClass.fakePost,
        onBackButtonClick = { }
    )
}