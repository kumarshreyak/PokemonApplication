package com.shrek.pokemonlibrary.client

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shrek.pokemonlibrary.ui.NoResultsText
import com.shrek.pokemonlibrary.ui.PokemonDescriptionText
import com.shrek.pokemonlibrary.ui.PokemonSpriteImage
import com.shrek.pokemonlibrary.ui.ShowRetryScreen

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
    modifier: Modifier? = null,
    imageModifier: Modifier? = null,
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
            if(showProgressLoader) CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = modifier ?: Modifier
            )
        }
        image!!.isSuccess() -> {
            Log.d("PokemonLogs", "PokemonSpriteResult: searchText = $searchText")
            PokemonSpriteImage(
                imageUrl = image!!.result!!.imgUrl,
                modifier = imageModifier ?: Modifier.size(200.dp)
            )
        }
        image!!.isError() -> {
            if(image!!.error?.httpFailureCode == 404) {
                Log.d("PokemonLogs", "NoResultsText: searchText = $searchText")
                if(showSearchFailure) NoResultsText(
                    searchText = searchText,
                    modifier = modifier ?: Modifier
                )
            } else {
                Log.d("PokemonLogs", "ShowRetryScreen: searchText = $searchText")
                if(showSearchFailure) ShowRetryScreen(
                    message = image!!.error?.errorMessage,
                    modifier = modifier ?: Modifier
                )
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
    modifier: Modifier? = null,
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
            if(showProgressLoader) CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = modifier ?: Modifier
            )
        }
        description!!.isSuccess() -> {
            Log.d("PokemonLogs", "PokemonSpriteResult: searchText = $searchText")
            PokemonDescriptionText(
                description = description!!.result!!,
                modifier = modifier ?: Modifier
            )
        }
        description!!.isError() -> {
            if(description!!.error?.httpFailureCode == 404) {
                Log.d("PokemonLogs", "NoResultsText: searchText = $searchText")
                if(showSearchFailure) NoResultsText(
                    searchText = searchText,
                    modifier = modifier ?: Modifier
                )
            } else {
                Log.d("PokemonLogs", "ShowRetryScreen: searchText = $searchText")
                if(showSearchFailure) ShowRetryScreen(
                    message = description!!.error?.errorMessage,
                    modifier = modifier ?: Modifier
                )
            }
        }
    }
}