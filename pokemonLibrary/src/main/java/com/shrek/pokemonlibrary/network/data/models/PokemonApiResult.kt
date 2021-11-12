package com.shrek.pokemonlibrary.network.data.models

import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.api.ResultState

/**
 * This is a wrapper class which will be used to return result after any SDK call
 * from [PokemonClient][com.shrek.pokemonlibrary.client.PokemonClient]
 * @property [resultState][ResultState] State of sdk call, one of - [ResultState.IN_PROGRESS], [ResultState.SUCCESS], [ResultState.ERROR]
 * @property [error] Error after completion of sdk call. @see [PokemonError]
 * @property [result] Result after sdk call.
 * eg. for [searchPokemonShakespeareDescription][com.shrek.pokemonlibrary.client.PokemonClient.searchPokemonShakespeareDescription] - [PokemonApiResult]<[String]> is returned
 */
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