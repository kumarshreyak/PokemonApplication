package com.shrek.pokemonlibrary.network.data.models

data class ApiError(val error: ErrorProp) : Throwable() {
    class ErrorProp(
        val message: String?,
        var code: Int? = null,
    )
}

fun Throwable.toPokemonError() : PokemonError {
    return if(this is ApiError) {
        PokemonError(errorMessage = error.message, httpFailureCode = error.code)
    } else {
        PokemonError(errorMessage = message)
    }
}