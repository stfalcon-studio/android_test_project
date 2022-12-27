package com.zipoapps.domain.usecases.post

import com.zipoapps.domain.models.Post
import com.zipoapps.domain.repositories.Repository
import com.zipoapps.domain.usecases.base.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class GetPostByUserUseCase constructor(
    private val repository: Repository
) : BaseUseCase<List<Post>, GetPostByUserUseCase.Params>() {

    override suspend fun remoteWork(params: Params?): List<Post> {
        return withContext(Dispatchers.IO) {
            repository.getPost().filter { it.userId == params!!.userId }
        }
    }

    data class Params(
        val userId: Int
    )
}