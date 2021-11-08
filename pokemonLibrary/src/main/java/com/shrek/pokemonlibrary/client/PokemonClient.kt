package com.shrek.pokemonlibrary.client

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shrek.pokemonlibrary.network.api.ResultState
import com.shrek.pokemonlibrary.network.data.models.*
import com.shrek.pokemonlibrary.network.data.models.GetShakespeareTextRequest
import com.shrek.pokemonlibrary.network.data.models.getEnglishDescription
import com.shrek.pokemonlibrary.network.data.models.isValid
import com.shrek.pokemonlibrary.network.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
    private var logLevel: PokemonClientLogLevel
) {

    private val _searchResult = MutableLiveData(PokemonApiResult())
    val pokemonSearchResult: LiveData<PokemonApiResult> = _searchResult

    fun searchPokemon(text: String) : LiveData<PokemonApiResult> {
        _searchResult.value = PokemonApiResult()
        CoroutineScope(Dispatchers.IO).launch {
            fetchPokemon(
                searchText = text,
                onError = { apiError ->
                    this.launch(context = Dispatchers.Main) {
                        _searchResult.value = PokemonApiResult().apply {
                            resultState = ResultState.ERROR
                            error = apiError?.toPokemonError()
                        }
                    }
                },
                onSuccess = { pokemon ->
                    this.launch(context = Dispatchers.Main) {
                        _searchResult.value = PokemonApiResult().apply {
                            resultState = ResultState.SUCCESS
                            result = pokemon
                        }
                    }
                },
            )
        }
        return _searchResult
    }


    private suspend fun fetchPokemon(
        searchText: String,
        onError: ((pokemonError: Throwable?) -> Unit)? = null,
        onSuccess: (pokemon: Pokemon) -> Unit
    ) {
        val pokemonResponse = MainRepository.getPokemon(searchText)
        when {
            pokemonResponse.isSuccess() -> {
                if(pokemonResponse.result?.isValid() == true) {
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
                    onError?.invoke(Throwable("Species/Image of pokemon not found"))
                }
            }
            pokemonResponse.isError() -> onError?.invoke(pokemonResponse.error)
            else -> Unit
        }
    }

    private suspend fun fetchPokemonSpecies(
        species: String,
        onError: ((error: Throwable?) -> Unit)? = null,
        onSuccess: (translatedDescription: String) -> Unit,
    ) {
        val pokemonSpeciesResponse = MainRepository.getPokemonSpecies(species)
        when {
            pokemonSpeciesResponse.isSuccess() -> {
                if(!pokemonSpeciesResponse.result?.getEnglishDescription().isNullOrBlank()) {
                    val englishDescription = pokemonSpeciesResponse.result?.getEnglishDescription()!!
                     fetchShakespeareDescription(text = englishDescription, onSuccess = onSuccess, onError = onError)
                } else {
                    onError?.invoke(Throwable("No description found for Pokemon"))
                }
            }
            pokemonSpeciesResponse.isError() -> onError?.invoke(pokemonSpeciesResponse.error)
            else -> Unit
        }
    }

    private suspend fun fetchShakespeareDescription(
        text: String,
        onSuccess: (description: String) -> Unit,
        onError: ((error: Throwable?) -> Unit)? = null,
    ) {
        val response = MainRepository.getShakespeareText(GetShakespeareTextRequest(text = text))
        when {
            response.isSuccess() -> {
                val translatedText = response.result?.contents?.translated
                if(translatedText.isNullOrBlank()) {
                    onError?.invoke(Throwable("No translation found for description of Pokemon"))
                } else {
                    onSuccess(translatedText)
                }
            }
            response.isError() -> onError?.invoke(Throwable(response.error))
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