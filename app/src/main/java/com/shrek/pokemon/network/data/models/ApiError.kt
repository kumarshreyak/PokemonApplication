package com.shrek.pokemon.network.data.models

data class ApiError(val error: ErrorProp) : Throwable() {
    class ErrorProp(
        val message: String?,
        var code: Int?
    )
}

/**
 *
 "error": {
        "code": 429,
        "message": "Too Many Requests: Rate limit of 5 requests per hour exceeded. Please wait for 57 minutes and 49 seconds."
    }
*/