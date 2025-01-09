package com.example.authapp.ui.screens

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.authapp.ui.model.Post
import com.example.authapp.ui.model.UserPosts
import com.example.authapp.ui.model.UserPostsUiState
import com.example.authapp.ui.screens.proccesing.ErrorScreen
import com.example.authapp.ui.screens.proccesing.LoadingScreen

@Composable
fun UserPostsScreen(
    modifier: Modifier = Modifier,
    userPostsUiState: UserPostsUiState,
    onBackButtonClick: () -> Unit,
    onPostClick: (Int) -> Unit
) {
    when(userPostsUiState) {
        is UserPostsUiState.Success -> {
            Scaffold(
                topBar = {
                    PostScreenTopBar(onBackButtonClick = onBackButtonClick)
                },
                modifier = modifier
            ) { innerPadding ->
                PostsList(
                    userPosts = userPostsUiState.userPosts,
                    onPostClick = onPostClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
        is UserPostsUiState.Loading -> {
            LoadingScreen()
        }
        is UserPostsUiState.Error -> {
            ErrorScreen()
        }
    }
}

@Composable
fun PostScreenTopBar(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = onBackButtonClick,
            modifier = Modifier.size(50.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back Button"
            )
        }
        Text(
            text = "Posts",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun PostsList(
    modifier: Modifier = Modifier,
    userPosts: UserPosts,
    onPostClick: (Int) -> Unit
) {
    if (userPosts.posts.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            items(userPosts.posts) { post ->
                UserPost(
                    modifier = Modifier.padding(8.dp),
                    post = post,
                    onPostClick = onPostClick
                )
            }
        }
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "There are no posts yet")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPost(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Int) -> Unit
) {
    Card(
        onClick = { onPostClick(post.id) },
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = post.body,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

//@Preview
//@Composable
//fun UserPostPreview() {
//    UserPost(
//        post = FakeDataClass.fakePost,
//        onPostClick = { }
//    )
//}
//
//@Preview
//@Composable
//fun PostsListPreview() {
//    PostsList(
//        userPosts = FakeDataClass.fakeUserPosts,
//        onPostClick = { }
//    )
//}
//
//@Preview
//@Composable
//fun PostScreenTopBarPreview() {
//    PostScreenTopBar(onBackButtonClick = { })
//}