package com.shrek.pokemon.network.api

import android.util.Log
import com.shrek.pokemon.network.data.models.ApiError
import retrofit2.Response

typealias NetworkCall<T> = suspend () -> Response<T>

private const val TAG = "apiCall"

suspend fun <T : Any> apiCall(block: NetworkCall<T>): ApiResult<T> {
    var result = ApiResult<T>()

    worker(block,
            { result = result.success(it) },
            { e, m, _ ->
                result = result.error(e, m)
            }
    )

    return result
}

private suspend fun <T> worker(
    block: NetworkCall<T>,
    onSuccess: (T) -> Unit,
    onFailure: (ApiError?, String?, String?) -> Unit
) {
    try {
        val response = block()

        val endPoint = response.raw().request.url.encodedPath

        Log.d(TAG, "request:  ${response.raw().request.body}")
        Log.d(TAG, "response:  ${response.body()}")

        if (response.isSuccessful) {
            val body = response.body()

            body?.let {
                onSuccess(it)
                Log.d(TAG, "completed")

            } ?: run {
                if (response.code() == 200) {
                    Log.d(TAG, "empty body on 200 response")
                }
                onFailure(ApiError(ApiError.ErrorProp("Unexpected response from server", response.code())), "Unexpected response", endPoint)
            }

        } else {
            Log.e(TAG, response.message())

            var errorMsg = "Something went wrong"
            try {
                val apiError = retrofitGson.fromJson(response.errorBody()?.charStream(), ApiError::class.java)
                apiError.error.code = response.code()
                Log.d(TAG, "api error $apiError")

                if (apiError.error.code in 400..499) {
                    onFailure(apiError, apiError.error.message, endPoint)
                } else {
                    // mask non-client error messages
                    onFailure(apiError, errorMsg, endPoint)
                }

            } catch (t: Throwable) {
                Log.w(TAG, "error in parsing error response to ApiError", t)
                errorMsg = response.message() ?: errorMsg
                onFailure(
                    ApiError(ApiError.ErrorProp(errorMsg, response.code())),
                    errorMsg,
                    endPoint
                )
            }
        }
    } catch (e: Throwable) {
        Log.e(TAG, "error", e)
        onFailure(
            ApiError(ApiError.ErrorProp("", 0)),
            "Something went wrong. Please check your connection and try again",
            ""
        )
    }

}
