package com.example.authapp.presentation.screens.nav

import androidx.lifecycle.ViewModel
import com.example.authapp.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel для [NavigationScreen].
 *
 * @property navigator объект [Navigator].
 */
@HiltViewModel
class NavigationScreenViewModel @Inject constructor(
    val navigator: Navigator,
) : ViewModel()