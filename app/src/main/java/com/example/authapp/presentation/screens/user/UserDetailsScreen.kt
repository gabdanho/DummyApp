package com.example.authapp.presentation.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun UserDetailsScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: UserDetailsScreenViewModel = hiltViewModel<UserDetailsScreenViewModel>(),
) {
    when(currentUserUiState) {
        is CurrentUserUiState.Success -> {
            UserDetails(
                modifier = modifier,
                currentUser = currentUserUiState.currentUser,
                onBackButtonClick = onBackButtonClick,
                onPostsClick = onPostsClick,
                isAnotherUser = isAnotherUser
            )
        }
        is CurrentUserUiState.Loading -> {
            LoadingScreen()
        }
        is CurrentUserUiState.Error -> {
            ErrorScreen()
        }
    }
}

@Composable
fun UserDetails(
    modifier: Modifier = Modifier,
    currentUser: User,
    onBackButtonClick: () -> Unit,
    onPostsClick: (Int) -> Unit,
    isAnotherUser: Boolean,
) {
    val columnNames = listOf(
        "Birthday" to currentUser.birthDate,
        "Age" to currentUser.age,
        "Gender" to currentUser.gender,
        "University" to currentUser.university,
        "Department" to currentUser.department,
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(currentUser.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = currentUser.firstName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = currentUser.lastName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Divider(color = Color.Black)
        LazyColumn {
            items(columnNames) { (column, userDetail) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (userDetail != null) {
                        Text(
                            text = column,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(120.dp)
                        )
                        Text(
                            text = userDetail
                        )
                    }
                }
            }
        }
        if (isAnotherUser) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(onClick = { onPostsClick(currentUser.id) }) {
                    Text(
                        text = "Posts",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(100.dp)
                    )
                }
            }
        }
    }
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = onBackButtonClick,
            modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

//@Preview
//@Composable
//fun UserDetailsPreview() {
//    UserDetails(
//        currentUser = FakeDataClass.fakeCurrentUser,
//        onBackButtonClick = { },
//        onPostsClick = { },
//        isAnotherUser = true
//    )
//}