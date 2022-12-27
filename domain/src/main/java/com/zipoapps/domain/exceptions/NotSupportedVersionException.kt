package com.zipoapps.domain.exceptions

import com.zipoapps.domain.models.errors.ApiError


class NotSupportedVersionException(val apiError: ApiError?): Throwable()