package com.example.authapp.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Контейнер с Pull-to-Refresh функционалом.
 *
 * @param isRefreshing Флаг состояния обновления.
 * @param onRefresh Лямбда вызывается при pull-to-refresh.
 * @param modifier Модификатор для настройки компонента.
 * @param content Содержимое, которое будет отображаться внутри контейнера.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshContainer(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (enabled) {
        val refreshState = rememberPullToRefreshState()

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = refreshState,
            onRefresh = onRefresh,
            modifier = modifier,
        ) {
            content()
        }
    } else content()
}