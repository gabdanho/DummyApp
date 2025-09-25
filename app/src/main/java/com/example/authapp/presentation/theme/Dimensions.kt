package com.example.authapp.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val none: Dp,
    val small: Dp,
    val medium: Dp,
    val userImageSize: Dp,
    val postsButtonWidth: Float,
    val columnDataWidth: Dp,
    val searchIconSize: Dp,
    val userImageInPersonItem: Dp,
    val cardElementImageSize: Dp,
    val fullWeight: Float,
    val halfWeight: Float,
    val imageBorder: Dp,
    val spacerHeight: Dp,
    val loginButtonTopPadding: Dp,
    val eyeIconSize: Dp,
    )

val defaultDimensions = Dimensions(
    none = 0.dp,
    small = 8.dp,
    medium = 16.dp,
    userImageSize = 100.dp,
    postsButtonWidth = 0.4f,
    columnDataWidth = 120.dp,
    searchIconSize = 50.dp,
    userImageInPersonItem = 60.dp,
    cardElementImageSize = 80.dp,
    fullWeight = 1f,
    halfWeight = 0.5f,
    imageBorder = 5.dp,
    spacerHeight = 24.dp,
    loginButtonTopPadding = 36.dp,
    eyeIconSize = 25.dp,
)