package com.shrek.pokemonlibrary.network.data.models

import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.api.ResultState

data class PokemonApiResult<T>(
    var resultState: ResultState = ResultState.IN_PROGRESS,
    var error: PokemonError? = null,
    var result: T? = null,
) {
    fun isInProgress() = resultState == ResultState.IN_PROGRESS
    fun isSuccess() = resultState == ResultState.SUCCESS && result != null
    fun isSuccessNonNull() = resultState == ResultState.SUCCESS && result != null
    fun isError() = resultState == ResultState.ERROR || result == null
}

data class PokemonError(
    val errorMessage: String?,
    val httpFailureCode: Int? = null,
)

fun <T> ApiResult<T>.toPokemonApiResult() : PokemonApiResult<T> {
    val pokemonError = if(error is ApiError?)
        PokemonError(error?.error?.message, error?.error?.code)
    else
        PokemonError(error?.message)

    return PokemonApiResult(resultState = resultState, result = result, error = pokemonError)
}