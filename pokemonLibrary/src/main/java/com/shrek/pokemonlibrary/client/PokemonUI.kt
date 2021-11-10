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
 * UI to display Pokemon image for the search.
 * @param searchText Text to search pokemon image for
 */
@Composable
fun PokemonSpriteUI(
    searchText: String?,
    showProgressLoader: Boolean = true,
    showSearchFailure: Boolean = true,
) {
    val image by PokemonClient.instance()._pokemonSpriteResponse.observeAsState()

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
 * UI to display Pokemon shakespeare description for the search.
 * @param searchText Text to search pokemon description for
 */
@Composable
fun PokemonShakespeareDescriptionUI(
    searchText: String?,
    showProgressLoader: Boolean = true,
    showSearchFailure: Boolean = true,
) {
    val description by PokemonClient.instance()._descriptionResponse.observeAsState()

    if(!searchText.isNullOrBlank())
        LaunchedEffect(key1 = searchText) {
            PokemonClient.instance().searchPokemonShakespeareDescription(pokemonName = searchText)
        }

    // Search Result Section
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