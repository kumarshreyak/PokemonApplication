package com.shrek.pokemonlibrary.network.repository

import com.shrek.pokemonlibrary.network.ShrekAPI
import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.api.apiCall
import com.shrek.pokemonlibrary.network.data.request.*
import com.shrek.pokemonlibrary.network.data.response.*

object MainRepository {

    suspend fun getPokemon(name: String) : ApiResult<GetPokemonResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemon(name = name) }
    }

    suspend fun getPokemonSpecies(species: String) : ApiResult<GetPokemonSpeciesResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemonSpecies(species = species) }
    }

    suspend fun getShakespeareText(request: GetShakespeareTextRequest) : ApiResult<GetShakespeareTextResponse> {
        return apiCall { ShrekAPI.mainInterface.getShakespeareText(request = request) }
    }
}