package com.shrek.pokemonlibrary.network.data.models

import com.shrek.pokemonlibrary.network.api.ResultState

data class PokemonApiResult<T>(
    var resultState: ResultState = ResultState.IN_PROGRESS,
    var error: PokemonError? = null,
    var result: T? = null,
) {
    fun isInProgress() = resultState == ResultState.IN_PROGRESS
    fun isSuccess() = resultState == ResultState.SUCCESS
    fun isError() = resultState == ResultState.ERROR

}

data class PokemonError(
    val errorMessage: String?,
    val httpFailureCode: Int? = null,
)