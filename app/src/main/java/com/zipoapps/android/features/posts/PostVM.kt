package com.zipoapps.android.features.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import com.zipoapps.android.common.base.BaseViewModel
import com.zipoapps.android.common.extensions.notifyValueChange
import com.zipoapps.android.common.extensions.withDefault
import com.zipoapps.domain.models.Post
import com.zipoapps.domain.models.User
import com.zipoapps.domain.usecases.base.ResultCallbacks
import com.zipoapps.domain.usecases.post.GetPostByUserUseCase

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class PostVM constructor(
    private val getPostUseCase: GetPostByUserUseCase
) : BaseViewModel() {

    val postList = MutableLiveData<MutableList<Post>>().withDefault(mutableListOf())
    val user = MutableLiveData<User>()

    fun setupData(user: User) {
        this.user.value = user
        loadPostForUser()
    }

    private fun loadPostForUser() {
        postList.value?.clear()

        user.value?.userId?.let { userId ->
            getPostUseCase.invoke(
                uiDispatcher = viewModelScope,
                params = GetPostByUserUseCase.Params(
                    userId = userId
                ),
                result = ResultCallbacks(
                    onSuccess = { posts ->
                        posts.onEach {
                            postList.value?.add(it)
                            postList.notifyValueChange()
                        }
                    },
                    onError = {
                        errorMessage.value = it.apiError?.toString()
                        Timber.e(it)
                    },
                    onLoading = {
                        isLoading.value = it
                    },
                    onConnectionError = {
                        Timber.e(it)
                        onConnectionError { loadPostForUser() }
                    },
                    onUnexpectedError = {
                        errorMessage.value = it.localizedMessage
                        Timber.e(it)
                    }
                )
            )
        }
    }
}