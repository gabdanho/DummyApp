package com.example.authapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.authapp.R
import com.example.authapp.ui.model.AuthUiState
import com.example.authapp.ui.model.UserLogin
import com.example.authapp.ui.screens.proccesing.ErrorScreen
import com.example.authapp.ui.screens.proccesing.LoadingScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    authUiState: AuthUiState,
    onLogoutClick: () -> Unit,
    onMyDetailsClick: () -> Unit,
    onMyPostsClick: () -> Unit,
    onFoundPersonClick: () -> Unit
) {
    when(authUiState) {
        is AuthUiState.Success -> {
            Scaffold(modifier = modifier) { innerPadding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    UserTopInfo(userLogin = authUiState.user)
                    CardsMain(
                        modifier = Modifier.padding(8.dp),
                        onMyDetailsClick = onMyDetailsClick,
                        onMyPostsClick = onMyPostsClick,
                        onFoundPersonClick = onFoundPersonClick
                    )
                }
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier.fillMaxSize()
                ) {
                    FloatingActionButton(
                        onClick = onLogoutClick,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = "Log out",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

            }
        }
        is AuthUiState.Error -> {
            ErrorScreen()
        }
        is AuthUiState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun UserTopInfo(
    modifier: Modifier = Modifier,
    userLogin: UserLogin
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(userLogin.image)
                .crossfade(true)
                .build(),
            contentDescription = "User Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = userLogin.firstName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = userLogin.lastName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun CardsMain(
    modifier: Modifier = Modifier,
    onMyDetailsClick: () -> Unit,
    onMyPostsClick: () -> Unit,
    onFoundPersonClick: () -> Unit
) {
    Column(modifier = modifier) {
        // Card: My Details
        createCard(
            imagerRes = R.drawable.user,
            cardName = "My Details",
            textStyle = MaterialTheme.typography.headlineLarge,
            onCardClick = onMyDetailsClick,
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            // Card: My Posts
            createCard(
                imagerRes = R.drawable.blogging,
                cardName = "My Posts",
                textStyle = MaterialTheme.typography.titleSmall,
                onCardClick = onMyPostsClick,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Card: Found Person
            createCard(
                imagerRes = R.drawable.multiple_users,
                cardName = "Found Person",
                textStyle = MaterialTheme.typography.titleSmall,
                onCardClick = onFoundPersonClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createCard(
    modifier: Modifier = Modifier,
    @DrawableRes imagerRes: Int,
    cardName: String,
    textStyle: TextStyle,
    onCardClick: () -> Unit
) {
    Card(
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(imagerRes),
                    contentDescription = cardName,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp)
                )
                Text(
                    text = cardName.uppercase(),
                    style = textStyle,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
        },
        onClick = onCardClick,
        modifier = modifier
    )
}

//@Preview
//@Composable
//fun MainScreenPreview() {
//    MainScreen(
//        authUiState = AuthUiState.Success(FakeDataClass.fakeUserLogin),
//        onLogoutClick = { },
//        onMyDetailsClick = { },
//        onMyPostsClick = { },
//        onFoundPersonClick = { }
//    )
//}