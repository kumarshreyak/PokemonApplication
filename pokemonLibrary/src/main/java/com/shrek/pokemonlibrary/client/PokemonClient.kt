package com.shrek.pokemonlibrary.client

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shrek.pokemonlibrary.network.api.ResultState
import com.shrek.pokemonlibrary.network.data.models.Pokemon
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult
import com.shrek.pokemonlibrary.network.data.models.PokemonError
import com.shrek.pokemonlibrary.network.data.request.GetShakespeareTextRequest
import com.shrek.pokemonlibrary.network.data.request.getEnglishDescription
import com.shrek.pokemonlibrary.network.data.response.getInvalidText
import com.shrek.pokemonlibrary.network.data.response.isValid
import com.shrek.pokemonlibrary.network.repository.MainRepository

class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
    private var logLevel: PokemonClientLogLevel
) {

    private val _searchResult = MutableLiveData(PokemonApiResult())
    val pokemonSearchResult: LiveData<PokemonApiResult> = _searchResult

    suspend fun searchPokemon(searchText: String) : LiveData<PokemonApiResult> {
        _searchResult.value = PokemonApiResult()
        fetchPokemon(
            searchText = searchText,
            onError = { pokemonError ->
                _searchResult.value = PokemonApiResult().apply {
                    resultState = ResultState.ERROR
                    error = pokemonError
                }
            },
            onSuccess = { pokemon ->
                _searchResult.value = PokemonApiResult().apply {
                    resultState = ResultState.SUCCESS
                    result = pokemon
                }
            },
        )
        return pokemonSearchResult
    }


    private suspend fun fetchPokemon(
        searchText: String,
        onError: ((pokemonError: PokemonError) -> Unit)? = null,
        onSuccess: (pokemon: Pokemon) -> Unit
    ) {
        val pokemonResponse = MainRepository.getPokemon(searchText)
        when {
            pokemonResponse.isSuccess() -> {
                if(pokemonResponse.result?.isValid() == false) {
                    val species = pokemonResponse.result.species?.name!!
                    val imgUrl = pokemonResponse.result.sprites?.frontDefault!!
                    fetchPokemonSpecies(species = species, onError = onError) {
                        onSuccess(
                            Pokemon(
                            name = pokemonResponse.result.name,
                            description = it,
                            imgUrl = imgUrl
                        )
                        )
                    }
                } else {
                    onError?.invoke(PokemonError(errorMessage = pokemonResponse.result?.getInvalidText()))
                }
            }
            pokemonResponse.isError() -> onError?.invoke(PokemonError(errorMessage = pokemonResponse.errorMessage)) // TODO add http error code here
            else -> Unit
        }
    }

    private suspend fun fetchPokemonSpecies(
        species: String,
        onError: ((pokemonError: PokemonError) -> Unit)? = null,
        onSuccess: (translatedDescription: String) -> Unit,
    ) {
        val pokemonSpeciesResponse = MainRepository.getPokemonSpecies(species)
        when {
            pokemonSpeciesResponse.isSuccess() -> {
                if(!pokemonSpeciesResponse.result?.getEnglishDescription().isNullOrBlank()) {
                    val englishDescription = pokemonSpeciesResponse.result?.getEnglishDescription()!!
                     fetchShakespeareDescription(text = englishDescription, onSuccess = onSuccess, onError = onError)
                } else {
                    onError?.invoke(PokemonError(errorMessage = "No description found for Pokemon")) // TODO maybe add better error message here
                }
            }
            pokemonSpeciesResponse.isError() -> onError?.invoke(PokemonError(errorMessage = pokemonSpeciesResponse.errorMessage))
            else -> Unit
        }
    }

    private suspend fun fetchShakespeareDescription(
        text: String,
        onSuccess: (description: String) -> Unit,
        onError: ((pokemonError: PokemonError) -> Unit)? = null,
    ) {
        val response = MainRepository.getShakespeareText(GetShakespeareTextRequest(text = text))
        when {
            response.isSuccess() -> {
                val translatedText = response.result?.contents?.translated
                if(translatedText.isNullOrBlank()) {
                    onError?.invoke(PokemonError(errorMessage = "No translation found for description of Pokemon"))
                } else {
                    onSuccess(translatedText)
                }
            }
            response.isError() -> onError?.invoke(PokemonError(errorMessage = response.errorMessage))
            else -> Unit
        }
    }


    /**
     * @param sdkKey Unique identifier for anyone using this SDK. Just a placeholder for now.
     * @param appContext Application Context of app using this sdk
     */
    class Builder(private val sdkKey: String = "SDK_KEY", private val appContext: Context) {
        // Configurable options here
        private var logLevel: PokemonClientLogLevel = PokemonClientLogLevel.DEBUG

        fun logLevel(level: PokemonClientLogLevel) : Builder {
            logLevel = level
            return this
        }

        fun build() : PokemonClient {
            return buildPokemonClient().also {
                instance = it
            }
        }

        private fun buildPokemonClient() : PokemonClient {
            return PokemonClient(sdkKey, appContext, logLevel)
        }
    }

    companion object {
        private var instance: PokemonClient? = null
        fun instance() : PokemonClient {
            return instance ?: throw IllegalStateException("PokemonClient.Builder::build() must be called before obtaining PokemonClient instance")
        }
    }
}

enum class PokemonClientLogLevel(private val severity: Int) {
    DEBUG(0),
    ERROR(1),
    NOTHING(2);
}