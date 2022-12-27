package com.zipoapps.domain.usecases.users

import com.zipoapps.domain.models.User
import com.zipoapps.domain.repositories.Repository
import com.zipoapps.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class SubscribeToUsersUseCase constructor(
    private val repository: Repository
) : BaseFlowUseCase<User, Unit>() {

    override suspend fun remoteWork(params: Unit?): Flow<User> {
        return withContext(Dispatchers.IO) {
            flow {
                val posts = repository.getPost()
                repository.getUsers().map { user ->
                    val postCount = posts.filter { it.userId == user.userId }.size
                    emit(user.copy(postCount = postCount))
                }
            }
        }
    }
}