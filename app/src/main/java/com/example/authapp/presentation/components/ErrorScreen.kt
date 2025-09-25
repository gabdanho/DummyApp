package com.example.authapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.authapp.presentation.model.LoadingState

@Composable
fun ErrorScreen(
    loadingState: LoadingState,
    onUpdateScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    PullToRefreshContainer(
        isRefreshing = loadingState is LoadingState.Loading,
        onRefresh = { onUpdateScreen() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Error",
                modifier = modifier.fillMaxSize()
            )
        }
    }
}