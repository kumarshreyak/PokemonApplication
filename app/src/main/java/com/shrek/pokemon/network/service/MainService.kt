package com.shrek.pokemon.network.service

import com.shrek.pokemon.network.data.request.*
import com.shrek.pokemon.network.data.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    @POST("/getPokemon")
    suspend fun getPokemon(@Body getPokemonRequest: GetPokemonRequest) : Response<GetPokemonResponse>
}