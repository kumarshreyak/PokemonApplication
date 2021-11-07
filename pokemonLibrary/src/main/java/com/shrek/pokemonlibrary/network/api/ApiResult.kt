package com.shrek.pokemonlibrary.network.api

data class ApiResult<T>(
        val resultState: ResultState = ResultState.IN_PROGRESS,
        val errorMessage: String? = null,
        val error: Throwable? = null,
        val result: T? = null) {

    fun isInProgress() = resultState == ResultState.IN_PROGRESS
    fun isSuccess() = resultState == ResultState.SUCCESS
    fun isError() = resultState == ResultState.ERROR

    fun success(result: T): ApiResult<T> {
        return copy(resultState = ResultState.SUCCESS, result = result)
    }

    fun error(t: Throwable?, message: String?): ApiResult<T> {
        return copy(resultState = ResultState.ERROR, error = t, errorMessage = message)
    }

    fun errorMessage(default: String): String {
        return errorMessage ?: default
    }

    companion object {
        fun <T> success(result: T): ApiResult<T> {
            return ApiResult(ResultState.SUCCESS, result = result)
        }

        fun <T> error(t: Throwable?, message: String?): ApiResult<T> {
            return ApiResult(resultState = ResultState.ERROR, error = t, errorMessage = message)
        }
    }
}

enum class ResultState {
    IN_PROGRESS, SUCCESS, ERROR
}

