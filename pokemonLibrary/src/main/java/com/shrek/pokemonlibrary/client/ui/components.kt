package com.shrek.pokemonlibrary.client.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.shrek.pokemonlibrary.R
import com.shrek.pokemonlibrary.client.PokemonClient


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

@Composable
fun NoResultsText(searchText: String? = null) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.no_results, searchText ?: "your search"),
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
    )
}

@Composable
fun ShowRetryScreen(message: String?) {
    Text(
        text = if(message.isNullOrBlank()) stringResource(id = R.string.generic_error) else message,
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PokemonSpriteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = stringResource(R.string.content_description_pokemon_image),
        modifier = modifier.wrapContentSize()
    )
}

@Composable
fun PokemonDescriptionText(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterHorizontally),
            text = description,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}