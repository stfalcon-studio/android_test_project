package com.zipoapps.domain.models

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
data class Post(
    val id: Int,
    val userId: Int,
    val body: String?,
    val title: String?
)