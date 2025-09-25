package com.example.authapp.presentation.utils

import android.content.Context
import android.widget.Toast
import com.example.authapp.presentation.model.UiMessage
import com.example.authapp.presentation.constants.TOAST_DURATION
import com.example.authapp.presentation.mapper.resources.StringToResourceIdMapperImpl
import kotlinx.coroutines.delay

/**
 * Отображает [UiMessage] через Toast.
 *
 * @receiver Контекст, используемый для отображения Toast
 * @param uiMessage Сообщение для отображения
 * @param clearMessage Функция для очистки сообщения после показа
 */
suspend fun Context.showUiMessage(uiMessage: UiMessage, clearMessage: () -> Unit) {
    if (uiMessage.textResName != null) {
        val resId = StringToResourceIdMapperImpl().map(uiMessage.textResName)
        Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
        delay(TOAST_DURATION)
    }
    if (uiMessage.details.isNotBlank()) {
        Toast.makeText(this, uiMessage.details, Toast.LENGTH_SHORT).show()
    }
}