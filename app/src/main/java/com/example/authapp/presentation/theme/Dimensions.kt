package com.example.authapp.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [Dimensions], [Float] компонентов приложения.
 */
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
    val loginElementWeight: Float,
    val imageBorder: Dp,
    val spacerHeight: Dp,
    val loginButtonTopPadding: Dp,
    val eyeIconSize: Dp,
    val loadingCircleSize: Dp,
    val circlesPaddingBetween: Dp,
    val travelDistance: Dp,
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
    loginElementWeight = 0.7f,
    imageBorder = 5.dp,
    spacerHeight = 24.dp,
    loginButtonTopPadding = 36.dp,
    eyeIconSize = 25.dp,
    loadingCircleSize = 25.dp,
    circlesPaddingBetween = 10.dp,
    travelDistance = 20.dp,
)