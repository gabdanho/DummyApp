package com.example.authapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authapp.ui.theme.AuthAppTheme
import com.example.authapp.ui.theme.screens.AuthScreen
import com.example.authapp.ui.theme.screens.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthAppTheme {
                AuthApp()
            }
        }
    }
}