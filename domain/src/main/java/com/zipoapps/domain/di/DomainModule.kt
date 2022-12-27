package com.zipoapps.domain.di

import com.zipoapps.domain.usecases.post.GetPostByUserUseCase
import com.zipoapps.domain.usecases.users.SubscribeToUsersUseCase
import org.koin.dsl.factory
import org.koin.dsl.module

private val useCasesModule = module {
    factory<SubscribeToUsersUseCase>()
    factory<GetPostByUserUseCase>()
}

val domainModule = arrayOf(
    useCasesModule
)
