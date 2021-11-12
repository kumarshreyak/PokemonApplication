package com.shrek.pokemonlibrary.client.datahelper

import com.shrek.pokemonlibrary.network.api.ApiResult
import com.shrek.pokemonlibrary.network.data.models.*
import com.shrek.pokemonlibrary.network.repository.MainRepository

internal suspend fun fetchPokemonSprite(pokemonName: String) : ApiResult<PokemonSprite> {
    val response = ApiResult<PokemonSprite>()
    val pokemonResponse = MainRepository.getPokemon(pokemonName)
    when {
        pokemonResponse.isSuccess() -> {
            return if(pokemonResponse.result?.hasSprite() == true) {
                val imgUrl = pokemonResponse.result.sprites?.frontDefault!!
                response.success(result = PokemonSprite(imgUrl = imgUrl))
            } else {
                response.error(
                    t = Throwable("Species/Image of pokemon not found"),
                    message = "Species/Image of pokemon not found"
                )
            }
        }
        pokemonResponse.isError() -> return response.error(
                t = pokemonResponse.error,
                message = pokemonResponse.errorMessage
            )
        else -> Unit
    }
    return response
}

internal suspend fun fetchPokemonShakespeareDescription(species: String) : ApiResult<String> {
    val response = ApiResult<String>()
    val pokemonSpeciesResponse = MainRepository.getPokemonSpecies(species)
    when {
        pokemonSpeciesResponse.isSuccess() -> {
            return if(!pokemonSpeciesResponse.result?.getEnglishDescription().isNullOrBlank()) {
                val englishDescription = pokemonSpeciesResponse.result?.getEnglishDescription()!!
                return fetchShakespeareDescription(text = englishDescription)
            } else {
                response.error(
                    t = Throwable("No description found for Pokemon"),
                    message = "No description found for Pokemon"
                )
            }
        }
        pokemonSpeciesResponse.isError() -> return response.error(
            t = pokemonSpeciesResponse.error,
            message = pokemonSpeciesResponse.errorMessage
        )
        else -> Unit
    }
    return response
}

internal suspend fun fetchShakespeareDescription(text: String) : ApiResult<String> {
    val finalResponse = ApiResult<String>()
    val response = MainRepository.getShakespeareText(GetShakespeareTextRequest(text = text))
    when {
        response.isSuccess() -> {
            val translatedText = response.result?.contents?.translated
            return if(translatedText.isNullOrBlank()) {
                finalResponse.error(
                    t = Throwable("No translation found for description of PokemonShakespeareDescription"),
                    message = "No translation found for description of PokemonShakespeareDescription"
                )
            } else {
                finalResponse.success(translatedText)
            }
        }
        response.isError() -> return finalResponse.error(Throwable(response.error), response.errorMessage)
        else -> Unit
    }
    return finalResponse
}