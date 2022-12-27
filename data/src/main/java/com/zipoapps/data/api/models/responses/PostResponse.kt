package com.zipoapps.data.api.models.responses

import com.google.gson.annotations.SerializedName
import com.template.domain.models.ModelMapper
import com.zipoapps.domain.models.Post

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
data class PostResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int
) {
    companion object : ModelMapper<Post, PostResponse> {
        override fun mapTo(model: Post): PostResponse {
            throw IllegalStateException("Unused convertation")
        }

        override fun mapToDomain(model: PostResponse): Post {
            return with(model) {
                Post(
                    id = id,
                    userId = userId,
                    body = body,
                    title = title
                )
            }
        }
    }
}