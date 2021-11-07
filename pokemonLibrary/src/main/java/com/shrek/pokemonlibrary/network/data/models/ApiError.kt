package com.shrek.pokemonlibrary.network.data.models

data class ApiError(val error: ErrorProp) : Throwable() {
    class ErrorProp(
        val message: String?,
        var code: Int?
    )
}

fun Throwable.toPokemonError() : PokemonError {
    return if(this is ApiError) {
        PokemonError(errorMessage = error.message, httpFailureCode = error.code)
    } else {
        PokemonError(errorMessage = message)
    }
}

/**
 *
 "error": {
        "code": 429,
        "message": "Too Many Requests: Rate limit of 5 requests per hour exceeded. Please wait for 57 minutes and 49 seconds."
    }
*/