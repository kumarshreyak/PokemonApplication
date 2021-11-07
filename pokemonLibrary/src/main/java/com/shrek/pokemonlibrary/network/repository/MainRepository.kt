package com.shrek.pokemonlibrary.network.repository

import com.shrek.pokemonlibrary.network.ShrekAPI
import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.api.ResultState
import com.shrek.pokemonlibrary.network.api.apiCall
import com.shrek.pokemonlibrary.network.api.retrofitGson
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult
import com.shrek.pokemonlibrary.network.data.request.*
import com.shrek.pokemonlibrary.network.data.response.*

object MainRepository {

    // Wrapper on response on pokemon api to get formatted result
    private const val POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/"
    suspend fun getPokemon(name: String) : ApiResult<GetPokemonResponse> {
        val result = ApiResult<GetPokemonResponse>()
        val responseString =  apiCall { ShrekAPI.mainInterface.getPokemon(url = POKEMON_URL + name) }
        when {
            responseString.isSuccess() -> {
                return result.copy(
                    resultState = ResultState.SUCCESS,
                    result = retrofitGson.fromJson(
                        responseString.result.toString(),
                        GetPokemonResponse::class.java
                    ),
                    errorMessage = null,
                    error = null,
                )
            }
            responseString.isError() -> {
                return result.copy(
                    resultState = ResultState.ERROR,
                    errorMessage = responseString.errorMessage,
                    error = responseString.error,
                    result = null,
                )
            }
            else -> return result
        }
    }

    private const val POKEMON_SPECIES_URL = "https://pokeapi.co/api/v2/pokemon-species/"
    suspend fun getPokemonSpecies(species: String) : ApiResult<GetPokemonSpeciesResponse> {
        return apiCall { ShrekAPI.mainInterface.getPokemonSpecies(url = POKEMON_SPECIES_URL + species) }
    }

    suspend fun getShakespeareText(request: GetShakespeareTextRequest) : ApiResult<GetShakespeareTextResponse> {
        return apiCall { ShrekAPI.mainInterface.getShakespeareText(request = request) }
    }
}