package com.shrek.pokemonlibrary.client

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shrek.pokemonlibrary.datahelper.fetchPokemonShakespeareDescription
import com.shrek.pokemonlibrary.datahelper.fetchPokemonSprite
import com.shrek.pokemonlibrary.network.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.Locale.ENGLISH

/**
 * This class can be used to perform different searches for pokemon names.
 * @see [searchPokemonSprite], [searchPokemonShakespeareDescription]
 */
class PokemonClient internal constructor(
    private val sdkKey: String,
    private val appContext: Context,
) {

    private var _descriptionResponse = MutableLiveData<PokemonApiResult<String>?>(null)
    /**
     * [descriptionResponse] is a Livedata that can be used to observe
     *  the response from [searchPokemonShakespeareDescription]
     */
    val descriptionResponse: LiveData<PokemonApiResult<String>?> = _descriptionResponse

    /**
     * Function to search for Shakespeare description of a Pokemon.
     * This is a suspending function which is safe to call from the main thread, it internally
     * uses the IO thread to make the API call.
     * @param pokemonName Pokemon name to search for
     * @return [PokemonApiResult]<String> search result for given pokemon name.
     */
    suspend fun searchPokemonShakespeareDescription(pokemonName: String) : PokemonApiResult<String>  {
        var response = PokemonApiResult<String>()
        _descriptionResponse.value = response
        withContext(Dispatchers.IO) {
            response = fetchPokemonShakespeareDescription(species = pokemonName.lowercase()).toPokemonApiResult()
        }
        withContext(Dispatchers.Main) {
            _descriptionResponse.value = response
        }
        return response
    }

    private var _pokemonSpriteResponse = MutableLiveData<PokemonApiResult<PokemonSprite>?>(null)
    /**
     * [descriptionResponse] is a Livedata that can be used to observe
     *  the response from [searchPokemonShakespeareDescription]`
     */
    val pokemonSpriteResponse: LiveData<PokemonApiResult<PokemonSprite>?> = _pokemonSpriteResponse
    /**
     * Function to search for sprite (image) of a Pokemon.
     * This is a suspending function which is safe to call from main thread, it internally
     * uses the IO thread to make the API call.
     * @param pokemonName Pokemon name to search for
     * @return [PokemonApiResult]<[PokemonSprite]> search result for given pokemon name.
     */
    suspend fun searchPokemonSprite(pokemonName: String) : PokemonApiResult<PokemonSprite>  {
        var response = PokemonApiResult<PokemonSprite>()
        _pokemonSpriteResponse.value = response
        withContext(Dispatchers.IO) {
            response = fetchPokemonSprite(pokemonName = pokemonName.lowercase()).toPokemonApiResult()
        }
        withContext(Dispatchers.Main) {
            _pokemonSpriteResponse.value = response
        }
        return response
    }

    /**
     * Builder class to build [PokemonClient]
     * @param sdkKey Unique identifier for anyone using this SDK. Just a placeholder for now.
     * @param appContext Application Context of app using this sdk.
     */
    class Builder(private val sdkKey: String = "SDK_KEY", private val appContext: Context) {
        // TODO add more methods for configuration here.

        fun build() : PokemonClient {
            return buildPokemonClient().also {
                instance = it
            }
        }

        private fun buildPokemonClient() : PokemonClient {
            return PokemonClient(sdkKey, appContext)
        }
    }

    companion object {
        private var instance: PokemonClient? = null

        /**
         * Make sure to [build][Builder.build] the PokemonClient before calling this function.
         * @see [Builder]
         */
        fun instance() : PokemonClient {
            return instance ?: throw IllegalStateException("PokemonClient.Builder::build() must be called before obtaining PokemonClient instance")
        }
    }
}