package com.example.authapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.authapp.presentation.navigation.AuthNavGraph

@Composable
fun AuthApp(navController: NavHostController = rememberNavController()) {
    AuthNavGraph(navController = navController)
}