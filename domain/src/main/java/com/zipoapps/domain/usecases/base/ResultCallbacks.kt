package com.zipoapps.domain.usecases.base

import com.zipoapps.domain.exceptions.ApiErrorException

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */

class ResultCallbacks<T>(
    val onSuccess: ((T) -> Unit)? = null,
    val onLoading: ((Boolean) -> Unit)? = null,
    val onError: ((ApiErrorException) -> Unit)? = null,
    val onConnectionError: ((Throwable) -> Unit)? = null,
    val onUnexpectedError: ((Throwable) -> Unit)? = null
)