package com.example.authapp.ui.theme.screens

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.authapp.data.NetworkUserRepository
import com.example.authapp.model.SearchPersonsUiState
import com.example.authapp.model.User
import com.example.authapp.model.fake.FakeDataClass
import com.example.authapp.network.mock.MockUserApiService

@Composable
fun SearchPersonScreen(
    authUserId: Int,
    authViewModel: AuthViewModel,
    onCurrentUserClick: (Int) -> Unit,
    onBackButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                authViewModel = authViewModel,
                onBackButtonClick = onBackButtonClick
            )
        }
    ) { innerPadding ->
        PersonsList(
            authUserId = authUserId,
            searchPersonsUiState = authViewModel.searchPersonsUiState,
            onCurrentUserClick = onCurrentUserClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun PersonsList(
    authUserId: Int,
    searchPersonsUiState: SearchPersonsUiState,
    onCurrentUserClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var userList by mutableStateOf<List<User>>(listOf())

    when(searchPersonsUiState) {
        is SearchPersonsUiState.Success -> {
            userList = searchPersonsUiState.userList.users
        }
        is SearchPersonsUiState.Error -> { }
        is SearchPersonsUiState.Loading -> { }
    }

    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(userList) { user ->
            if (user.id == authUserId) { }
            else {
                PersonItem(
                    user = user,
                    onCurrentUserClick = onCurrentUserClick
                )
            }
        }
    }
}

@Composable
fun SearchAppBar(
    authViewModel: AuthViewModel,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(8.dp)
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
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                authViewModel.updateSearchText(searchText)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.height(50.dp)
        )
    }
}

@Composable
fun PersonItem(
    user: User,
    onCurrentUserClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onCurrentUserClick(user.id) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.image)
                .crossfade(true)
                .build(),
            contentDescription = "User Image",
            modifier = Modifier.size(60.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
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

@Preview
@Composable
fun SearchPersonScreenPreview() {
    val mockUserRepository = NetworkUserRepository(MockUserApiService())
    val authViewModel = AuthViewModel(mockUserRepository)
    SearchPersonScreen(
        authUserId = -1,
        authViewModel = authViewModel,
        onBackButtonClick = { },
        onCurrentUserClick =  { }
    )
}

@Preview
@Composable
fun PersonItemPreview() {
    PersonItem(
        user = FakeDataClass.fakeCurrentUser,
        onCurrentUserClick = { }
    )
}