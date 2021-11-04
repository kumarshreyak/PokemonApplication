package com.shrek.pokemon.network.repository

import com.shrek.pokemon.network.ShrekAPI
import com.shrek.pokemon.network.api.ApiResult
import com.shrek.pokemon.network.api.apiCall
import com.shrek.pokemon.network.data.request.*
import com.shrek.pokemon.network.data.response.*

object MainRepository {

    suspend fun getPokemon(request: GetPokemonRequest) : ApiResult<GetPokemonResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemon(getPokemonRequest = request) }
    }
}