package com.example.authapp.presentation.nav

import androidx.lifecycle.ViewModel
import com.example.authapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationScreenViewModel @Inject constructor(
    val navigator: Navigator,
) : ViewModel()