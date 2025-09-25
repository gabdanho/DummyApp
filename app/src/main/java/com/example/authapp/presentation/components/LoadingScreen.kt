package com.example.authapp.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.authapp.presentation.theme.defaultDimensions
import kotlinx.coroutines.delay

/**
 * Экран загрузки.
 *
 * Отображает анимированные круги-индикаторы загрузки.
 *
 * @param modifier [Modifier] для настройки внешнего вида.
 * @param circleSize размер окружностей-индикаторов.
 * @param circleColor цвет окружностей-индикаторов.
 * @param spaceBetween расстояние между окружностями.
 * @param travelDistance амплитуда движения окружностей.
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    circleSize: Dp = defaultDimensions.loadingCircleSize,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    spaceBetween: Dp = defaultDimensions.circlesPaddingBetween,
    travelDistance: Dp = defaultDimensions.travelDistance,
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 using LinearOutSlowInEasing
                        1.0f at 300 using LinearOutSlowInEasing
                        0.0f at 600 using LinearOutSlowInEasing
                        0.0f at 1200 using LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    val lastCircle = circleValues.size - 1

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row{
            circleValues.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .graphicsLayer {
                            translationY = -value * distance
                        }
                        .background(
                            color = circleColor,
                            shape = CircleShape
                        )
                )

                if(index != lastCircle) {
                    Spacer(modifier = Modifier.width(spaceBetween))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}