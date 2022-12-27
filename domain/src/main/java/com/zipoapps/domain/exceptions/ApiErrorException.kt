package com.zipoapps.domain.exceptions

import com.zipoapps.domain.models.errors.ApiError


class ApiErrorException(val apiError: ApiError?) : Throwable()
