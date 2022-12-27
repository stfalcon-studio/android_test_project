package com.zipoapps.data.api.services

import com.zipoapps.data.api.models.responses.PostResponse
import com.zipoapps.data.api.models.responses.UserResponse
import retrofit2.http.GET

interface CommonService {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
