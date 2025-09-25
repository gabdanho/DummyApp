package com.example.authapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.authapp.presentation.screens.nav.NavigationScreen
import com.example.authapp.presentation.theme.AuthAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Главная активность приложения.
 *
 * Использует [AuthAppTheme] для применения цветовой схемы и стилей.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthAppTheme {
                Box(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                ) {
                    NavigationScreen()
                }
            }
        }
    }
}