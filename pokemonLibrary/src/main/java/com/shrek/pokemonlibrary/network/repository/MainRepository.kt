package com.shrek.pokemonlibrary.network.repository

import com.shrek.pokemonlibrary.network.ShrekAPI
import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.api.apiCall
import com.shrek.pokemonlibrary.network.data.models.GetPokemonResponse
import com.shrek.pokemonlibrary.network.data.models.GetPokemonSpeciesResponse
import com.shrek.pokemonlibrary.network.data.models.GetShakespeareTextRequest
import com.shrek.pokemonlibrary.network.data.models.GetShakespeareTextResponse

internal object MainRepository {

    private const val POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/"
    suspend fun getPokemon(name: String) : ApiResult<GetPokemonResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemon(url = POKEMON_URL + name) }
    }

    private const val POKEMON_SPECIES_URL = "https://pokeapi.co/api/v2/pokemon-species/"
    suspend fun getPokemonSpecies(species: String) : ApiResult<GetPokemonSpeciesResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemonSpecies(url = POKEMON_SPECIES_URL + species) }
    }

    suspend fun getShakespeareText(request: GetShakespeareTextRequest) : ApiResult<GetShakespeareTextResponse> {
        return apiCall { ShrekAPI.mainInterface.getShakespeareText(request = request) }
    }
}