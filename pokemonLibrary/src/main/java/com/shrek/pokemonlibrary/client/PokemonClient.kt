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

class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
    private var logLevel: PokemonClientLogLevel
) {

    private val _searchResult = MutableLiveData(PokemonApiResult<PokemonShakespeareDescription>())
    val pokemonShakespeareDescriptionSearchResult: LiveData<PokemonApiResult<PokemonShakespeareDescription>> = _searchResult

    fun searchPokemonShakespeareDescription(pokemonName: String) : PokemonApiResult<PokemonShakespeareDescription> {
        val apiResult = PokemonApiResult<PokemonShakespeareDescription>()
        _searchResult.value = apiResult
        CoroutineScope(Dispatchers.IO).launch {
            fetchPokemon(
                searchText = pokemonName,
                onError = { apiError ->
                    this.launch(context = Dispatchers.Main) {
                        apiResult.apply {
                            resultState = ResultState.ERROR
                            error = apiError?.toPokemonError()
                        }
                        _searchResult.value = apiResult
                    }
                },
                onSuccess = { pokemon ->
                    this.launch(context = Dispatchers.Main) {
                        apiResult.apply {
                            resultState = ResultState.SUCCESS
                            result = pokemon
                        }
                        _searchResult.value = apiResult
                    }
                },
            )
        }
        return apiResult
    }


    private suspend fun fetchPokemon(
        searchText: String,
        onError: ((pokemonError: Throwable?) -> Unit)? = null,
        onSuccess: (pokemonShakespeareDescription: PokemonShakespeareDescription) -> Unit
    ) {
        val pokemonResponse = MainRepository.getPokemon(searchText)
        when {
            pokemonResponse.isSuccess() -> {
                if(pokemonResponse.result?.isValid() == true) {
                    val species = pokemonResponse.result.species?.name!!
                    val imgUrl = pokemonResponse.result.sprites?.frontDefault!!
                    fetchPokemonSpecies(species = species, onError = onError) {
                        onSuccess(
                                PokemonShakespeareDescription(
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
                    onError?.invoke(Throwable("No description found for PokemonShakespeareDescription"))
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
                    onError?.invoke(Throwable("No translation found for description of PokemonShakespeareDescription"))
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