package com.shrek.pokemonlibrary.client

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.shrek.pokemonlibrary.client.ui.NoResultsText
import com.shrek.pokemonlibrary.client.ui.PokemonDescriptionText
import com.shrek.pokemonlibrary.client.ui.PokemonSpriteImage
import com.shrek.pokemonlibrary.client.ui.ShowRetryScreen

/**
 * UI to display Pokemon's sprite (image).
 * This UI internally manages the search and progress/error states all you need to provide is the [searchText].
 * The state of the search performed here can be observed by observing [PokemonClient.pokemonSpriteResponse] livedata.
 * @param searchText Pokemon name to search for.
 */
@Composable
fun PokemonSpriteUI(
    searchText: String?,
    showProgressLoader: Boolean = true,
    showSearchFailure: Boolean = true,
) {
    val image by PokemonClient.instance().pokemonSpriteResponse.observeAsState()

    if(!searchText.isNullOrBlank())
        LaunchedEffect(key1 = searchText) {
            PokemonClient.instance().searchPokemonSprite(pokemonName = searchText)
        }

    // Search Result Section
    when {
        image == null || searchText.isNullOrBlank() -> Unit
        image!!.isInProgress() -> {
            Log.d("PokemonLogs", "CircularProgressIndicator: searchText = $searchText")
            if(showProgressLoader) CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        image!!.isSuccess() -> {
            Log.d("PokemonLogs", "PokemonSpriteResult: searchText = $searchText")
            PokemonSpriteImage(imageUrl = image!!.result!!.imgUrl)
        }
        image!!.isError() -> {
            if(image!!.error?.httpFailureCode == 404) {
                Log.d("PokemonLogs", "NoResultsText: searchText = $searchText")
                if(showSearchFailure) NoResultsText(searchText = searchText)
            } else {
                Log.d("PokemonLogs", "ShowRetryScreen: searchText = $searchText")
                if(showSearchFailure) ShowRetryScreen(message = image!!.error?.errorMessage)
            }
        }
    }
}

/**
 * UI to display Pokemon's shakespeare description.
 * This UI internally manages the search and progress/error states all you need to provide is the [searchText].
 * The state of the search performed here can be observed by observing [PokemonClient.descriptionResponse] livedata.
 * @param searchText Pokemon name to search for.
 */
@Composable
fun PokemonShakespeareDescriptionUI(
    searchText: String?,
    showProgressLoader: Boolean = true,
    showSearchFailure: Boolean = true,
) {
    val description by PokemonClient.instance().descriptionResponse.observeAsState()

    if(!searchText.isNullOrBlank())
        LaunchedEffect(key1 = searchText) {
            PokemonClient.instance().searchPokemonShakespeareDescription(pokemonName = searchText)
        }

    when {
        description == null || searchText.isNullOrBlank() -> Unit
        description!!.isInProgress() -> {
            Log.d("PokemonLogs", "CircularProgressIndicator: searchText = $searchText")
            if(showProgressLoader) CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        description!!.isSuccess() -> {
            Log.d("PokemonLogs", "PokemonSpriteResult: searchText = $searchText")
            PokemonDescriptionText(description = description!!.result!!)
        }
        description!!.isError() -> {
            if(description!!.error?.httpFailureCode == 404) {
                Log.d("PokemonLogs", "NoResultsText: searchText = $searchText")
                if(showSearchFailure) NoResultsText(searchText = searchText)
            } else {
                Log.d("PokemonLogs", "ShowRetryScreen: searchText = $searchText")
                if(showSearchFailure) ShowRetryScreen(message = description!!.error?.errorMessage)
            }
        }
    }
}