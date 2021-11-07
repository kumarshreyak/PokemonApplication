package com.shrek.pokemonlibrary.network.service

import com.shrek.pokemonlibrary.network.data.request.*
import com.shrek.pokemonlibrary.network.data.response.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {
    @GET
    suspend fun getPokemon(
        @Url url: String,
    ) : Response<GetPokemonResponse>

    @GET
    suspend fun getPokemonSpecies(
        @Url url: String,
    ) : Response<GetPokemonSpeciesResponse>

    @POST
    suspend fun getShakespeareText(
        @Url url: String = "https://api.funtranslations.com/translate/shakespeare",
        @Body request: GetShakespeareTextRequest
    ) : Response<GetShakespeareTextResponse>
}