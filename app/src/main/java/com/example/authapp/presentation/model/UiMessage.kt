package com.example.authapp.presentation.model

data class UiMessage(
    val id: Long = System.currentTimeMillis(),
    val textResName: StringResNamePresentation? = null,
    val details: String = "",
)
