package com.zipoapps.android.features.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import com.zipoapps.android.common.base.BaseViewModel
import com.zipoapps.android.common.extensions.notifyValueChange
import com.zipoapps.android.common.extensions.withDefault
import com.zipoapps.domain.models.User
import com.zipoapps.domain.usecases.base.ResultCallbacks
import com.zipoapps.domain.usecases.users.SubscribeToUsersUseCase

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class InfoVM constructor(
    private val subscribeUsersUseCase: SubscribeToUsersUseCase
): BaseViewModel() {

    val userList = MutableLiveData<MutableList<User>>().withDefault(mutableListOf())

    init {
        subscribeToUsers()
    }

    private fun subscribeToUsers() {
        subscribeUsersUseCase.invoke(
            uiDispatcher = viewModelScope,
            result = ResultCallbacks(
                onSuccess = { user ->
                    userList.value?.add(user)
                    userList.notifyValueChange()
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
                    onConnectionError { subscribeToUsers() }
                },
                onUnexpectedError = {
                    errorMessage.value = it.localizedMessage
                    Timber.e(it)
                }
            )
        )
    }
}