package com.zipoapps.data.repositories

import com.zipoapps.data.api.models.responses.PostResponse
import com.zipoapps.data.api.models.responses.UserResponse
import com.zipoapps.data.api.services.CommonService
import com.zipoapps.data.extensions.mapToApiErrors
import com.zipoapps.domain.models.Post
import com.zipoapps.domain.models.User
import com.zipoapps.domain.repositories.Repository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RepositoryImpl(
    private val networkService: CommonService
) : Repository {

    override suspend fun getUsers(): List<User> {
        try {
            return networkService.getUsers().map { UserResponse.mapToDomain(it) }
        } catch (e: Throwable) {
            throw e.mapToApiErrors()
        }
    }

    override suspend fun getUsersFlow(): Flow<User> = callbackFlow {
        try {
            networkService.getUsers().map { userResponse ->
                this.channel.send(UserResponse.mapToDomain(userResponse))
            }

            awaitClose { close() }
        } catch (e: Throwable) {
            throw e.mapToApiErrors()
        }
    }

    override suspend fun getPost(): List<Post> {
        try {
            delay(500)
            return networkService.getPosts().map { PostResponse.mapToDomain(it) }
        } catch (e: Throwable) {
            throw e.mapToApiErrors()
        }
    }
}
