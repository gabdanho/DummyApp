package com.example.authapp.presentation.mapper.resources


import com.example.authapp.R
import com.example.authapp.presentation.model.StringResNamePresentation

/**
 * Реализация маппера, которая сопоставляет все возможные
 * идентификаторы из presentation слоя с конкретными ресурсами строк в R.string.
 * Используется для отображения локализованных текстов в UI.
 */
class StringToResourceIdMapperImpl : StringToResourceIdMapper {
    private val resourceMapPresentation = mapOf(
        StringResNamePresentation.ERROR_GET_USER to R.string.error_get_user,
        StringResNamePresentation.ERROR_AUTH to R.string.error_auth,
        StringResNamePresentation.ERROR_ALL_FIELDS_NOT_FILLED to R.string.error_all_fields_not_filled,
        StringResNamePresentation.ERROR_GET_POST to R.string.error_get_post,
        StringResNamePresentation.ERROR_GET_USER_POSTS to R.string.error_get_user_posts,
        StringResNamePresentation.ERROR_GET_USERS to R.string.error_get_users,
        StringResNamePresentation.ERROR_GET_COMMENTS to R.string.error_get_comments
    )

    override fun map(resId: StringResNamePresentation): Int {
        return resourceMapPresentation[resId]
            ?: throw IllegalArgumentException("CANT_MAP_THIS_ID_$resId")
    }
}