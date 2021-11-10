package com.shrek.pokemonlibrary.client

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.shrek.pokemonlibrary.client.datahelper.fetchPokemonShakespeareDescription
import com.shrek.pokemonlibrary.client.datahelper.fetchPokemonSprite
import com.shrek.pokemonlibrary.network.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
    private var logLevel: PokemonClientLogLevel
) {

    internal var descriptionResponse = MutableLiveData<PokemonApiResult<String>?>(null)
    suspend fun searchPokemonShakespeareDescription(pokemonName: String) : PokemonApiResult<String>  {
        var response = PokemonApiResult<String>()
        descriptionResponse.value = response
        withContext(Dispatchers.IO) {
            response = fetchPokemonShakespeareDescription(species = pokemonName).toPokemonApiResult()
        }
        descriptionResponse.value = response
        return response
    }

    internal var pokemonSpriteResponse = MutableLiveData<PokemonApiResult<PokemonSprite>?>(null)
    suspend fun searchPokemonSprite(pokemonName: String) : PokemonApiResult<PokemonSprite>  {
        var response = PokemonApiResult<PokemonSprite>()
        pokemonSpriteResponse.value = response
        withContext(Dispatchers.IO) {
            response = fetchPokemonSprite(pokemonName = pokemonName).toPokemonApiResult()
        }
        pokemonSpriteResponse.value = response
        return response
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