package com.shrek.pokemon.network.service

import com.shrek.pokemon.network.data.request.*
import com.shrek.pokemon.network.data.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface MainService {
    @POST("https://api.funtranslations.com/translate/shakespeare")
    suspend fun getPokemon(@Body request: GetPokemonRequest) : Response<GetPokemonResponse>

    @POST
    suspend fun getShakespeareText(
        @Url url: String = "https://api.funtranslations.com/translate/shakespeare",
        @Body request: GetShakespeareTextRequest
    ) : Response<GetShakespeareTextResponse>
}