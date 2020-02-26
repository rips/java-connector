package com.ripstech.api.utils.kotlin

import com.ripstech.api.connector.ApiResponse
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.utils.MinimalLogging
import java.util.function.Consumer

sealed class ApiResult<T>
data class Failure<T>(val httpStatus: Int, val message: String) : ApiResult<T>() {
    fun exception(): ApiException {
        return ApiException(httpStatus, message)
    }
}
data class Success<T>(val value: T) : ApiResult<T>()

fun <T> ApiResponse<T>.result(): ApiResult<T> {
    return if (this.isOk) {
        Success(this.orNull())
    } else {
        Failure(this.status, this.message)
    }
}

fun MinimalLogging.setLogger(logger: (String) -> Unit) {
    setLogger(Consumer(logger))
}
