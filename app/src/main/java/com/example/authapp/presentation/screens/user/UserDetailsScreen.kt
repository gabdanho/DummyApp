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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.authapp.presentation.model.LoadingState
import com.example.authapp.presentation.model.user.User
import com.example.authapp.presentation.screens.proccesing.ErrorScreen
import com.example.authapp.presentation.screens.proccesing.LoadingScreen
import com.example.authapp.presentation.theme.defaultDimensions
import com.example.authapp.presentation.utils.showUiMessage

@Composable
fun UserDetailsScreen(
    id: Int,
    modifier: Modifier = Modifier,
    isAnotherUser: Boolean = false,
    viewModel: UserDetailsScreenViewModel = hiltViewModel<UserDetailsScreenViewModel>(),
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
        viewModel.getUser(userId = id)
    }

    Surface {
        when (uiState.loadingState) {
            is LoadingState.Success -> {
                UserDetails(
                    currentUser = uiState.user,
                    onBackButtonClick = { viewModel.onBackButtonClick() },
                    onPostsClick = { viewModel.onUserPostsClick(userId = it) },
                    isAnotherUser = isAnotherUser,
                    modifier = modifier.fillMaxWidth(),
                )
            }

            is LoadingState.Loading -> {
                LoadingScreen()
            }

            is LoadingState.Error -> {
                ErrorScreen()
            }

            null -> {}
        }
    }
}

@Composable
private fun UserDetails(
    currentUser: User,
    isAnotherUser: Boolean,
    onBackButtonClick: () -> Unit,
    onPostsClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val columnNames = listOf(
        "Birthday" to currentUser.birthDate,
        "Age" to currentUser.age,
        "Gender" to currentUser.gender,
        "University" to currentUser.university,
        "Department" to currentUser.department,
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(defaultDimensions.medium)
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
                modifier = Modifier.size(defaultDimensions.userImageSize)
            )
            Column(modifier = Modifier.padding(start = defaultDimensions.medium)) {
                Text(
                    text = currentUser.firstName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = defaultDimensions.small)
                )
                Text(
                    text = currentUser.lastName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        HorizontalDivider(Modifier, DividerDefaults.Thickness, color = MaterialTheme.colorScheme.onBackground)
        LazyColumn {
            items(columnNames) { (columnName, details) ->
                ColumnData(
                    columnName = columnName,
                    details = details,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(defaultDimensions.small)
                )
            }
        }
        if (isAnotherUser) {
            Row(
                modifier = Modifier
                    .padding(start = defaultDimensions.small)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(onClick = { onPostsClick(currentUser.id) }) {
                    Text(
                        text = "Posts",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(defaultDimensions.postsButtonWidth)
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
                .padding(bottom = defaultDimensions.small, start = defaultDimensions.small)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
private fun ColumnData(
    columnName: String,
    details: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = columnName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(defaultDimensions.columnDataWidth)
        )
        Text(text = details)
    }
}