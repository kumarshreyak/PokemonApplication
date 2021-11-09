package com.shrek.pokemonlibrary.client

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shrek.pokemonlibrary.network.api.ResultState
import com.shrek.pokemonlibrary.network.data.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
    private var logLevel: PokemonClientLogLevel
) {

//    private val _pokemonShakespeareDescriptionSearchResult = MutableLiveData(PokemonApiResult<PokemonShakespeareDescription>())
//    val pokemonShakespeareDescriptionSearchResult: LiveData<PokemonApiResult<PokemonShakespeareDescription>> = _pokemonShakespeareDescriptionSearchResult

//    fun searchPokeShakespeareDescription(pokemonName: String) : PokemonApiResult<PokemonShakespeareDescription> {
//        val apiResult = PokemonApiResult<PokemonShakespeareDescription>()
//        _pokemonShakespeareDescriptionSearchResult.value = apiResult
//        CoroutineScope(Dispatchers.IO).launch {
//            fetchPokemonDescription(
//                searchText = pokemonName,
//                onError = { apiError ->
//                    this.launch(context = Dispatchers.Main) {
//                        apiResult.apply {
//                            resultState = ResultState.ERROR
//                            error = apiError?.toPokemonError()
//                        }
//                        _pokemonShakespeareDescriptionSearchResult.value = apiResult
//                    }
//                },
//                onSuccess = { pokemon ->
//                    this.launch(context = Dispatchers.Main) {
//                        apiResult.apply {
//                            resultState = ResultState.SUCCESS
//                            result = pokemon
//                        }
//                        _pokemonShakespeareDescriptionSearchResult.value = apiResult
//                    }
//                },
//            )
//        }
//        return apiResult
//    }

    fun searchPokemonShakespeareDescription(pokemonName: String) : PokemonApiResult<String>  {
        var response = PokemonApiResult<String>()
        CoroutineScope(Dispatchers.IO).launch {
            response = fetchPokemonShakespeareDescription(species = pokemonName).toPokemonApiResult()
        }
        return response
    }

    fun searchPokemonSprite(pokemonName: String) : PokemonApiResult<PokemonSprite>  {
        var response = PokemonApiResult<PokemonSprite>()
        CoroutineScope(Dispatchers.IO).launch {
            response = fetchPokemonSprite(pokemonName = pokemonName).toPokemonApiResult()
        }
        return response
    }

//        private val _pokemonSpriteSearchResult = MutableLiveData(PokemonApiResult<PokemonSprite>())
//        val pokemonSpriteSearchResult: LiveData<PokemonApiResult<PokemonSprite>> = _pokemonSpriteSearchResult
//
//        fun searchPokemonSprite(pokemonName: String) : PokemonApiResult<PokemonSprite> {
//            val apiResult = PokemonApiResult<PokemonSprite>()
//            _pokemonSpriteSearchResult.value = apiResult
//            CoroutineScope(Dispatchers.IO).launch {
//                fetchPokemonDescription(
//                    searchText = pokemonName,
//                    onError = { apiError ->
//                        this.launch(context = Dispatchers.Main) {
//                            apiResult.apply {
//                                resultState = ResultState.ERROR
//                                error = apiError?.toPokemonError()
//                            }
//                            _pokemonSpriteSearchResult.value = apiResult
//                        }
//                    },
//                    onSuccess = { pokemon ->
//                        this.launch(context = Dispatchers.Main) {
//                            apiResult.apply {
//                                resultState = ResultState.SUCCESS
//                                result = pokemon
//                            }
//                            _pokemonSpriteSearchResult.value = apiResult
//                        }
//                    },
//                )
//            }
//            return apiResult
//        }





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