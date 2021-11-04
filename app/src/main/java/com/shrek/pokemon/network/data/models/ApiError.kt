package com.shrek.pokemon.network.data.models

data class ApiError(val code: String?,
                    val msg: String?,
                    var httpCode: Int?) : Throwable()