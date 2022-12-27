package com.zipoapps.domain.repositories

import com.zipoapps.domain.models.Post
import com.zipoapps.domain.models.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getUsers(): List<User>

    suspend fun getUsersFlow(): Flow<User>

    suspend fun getPost(): List<Post>
}
