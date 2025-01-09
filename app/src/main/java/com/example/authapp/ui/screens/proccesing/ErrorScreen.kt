package com.example.authapp.ui.screens.proccesing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Filled.Clear,
        contentDescription = "Error",
        modifier = modifier.fillMaxSize()
    )
}