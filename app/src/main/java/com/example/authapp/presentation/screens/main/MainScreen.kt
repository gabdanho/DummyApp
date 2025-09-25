package com.example.authapp.presentation.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.authapp.R
import com.example.authapp.presentation.model.user.UserLogin

@Composable
fun MainScreen(
    userLogin: UserLogin,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>(),
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            UserTopInfo(
                userLogin = userLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            MainCards(
                modifier = Modifier.padding(8.dp),
                onMyDetailsClick = { viewModel.onMyDetailsClick(userId = userLogin.id) },
                onMyPostsClick = { viewModel.onMyPostsClick(userId = userLogin.id) },
                onFoundPersonClick = { viewModel.onFoundPersonClick(authUserId = userLogin.id) }
            )
        }
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()
        ) {
            FloatingActionButton(
                onClick = { viewModel.onLogoutClick() },
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

@Composable
private fun UserTopInfo(
    userLogin: UserLogin,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
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
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = userLogin.firstName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = userLogin.lastName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun MainCards(
    onMyDetailsClick: () -> Unit,
    onMyPostsClick: () -> Unit,
    onFoundPersonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        // Card: My Details
        CardElement(
            imageRes = R.drawable.user,
            cardName = "My Details",
            textStyle = MaterialTheme.typography.headlineLarge,
            onCardClick = onMyDetailsClick,
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            // Card: My Posts
            CardElement(
                imageRes = R.drawable.blogging,
                cardName = "My Posts",
                textStyle = MaterialTheme.typography.titleSmall,
                onCardClick = onMyPostsClick,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Card: Found Person
            CardElement(
                imageRes = R.drawable.multiple_users,
                cardName = "Found Person",
                textStyle = MaterialTheme.typography.titleSmall,
                onCardClick = onFoundPersonClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CardElement(
    @DrawableRes imageRes: Int,
    cardName: String,
    textStyle: TextStyle,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onCardClick,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = cardName,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
            )
            Text(
                text = cardName.uppercase(),
                style = textStyle,
                fontWeight = FontWeight.Bold
            )
        }
    }
}