package com.shrek.pokemonlibrary.ui

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.shrek.pokemonlibrary.R
import com.shrek.pokemonlibrary.client.PokemonClient


@Composable
internal fun NoResultsText(searchText: String? = null) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.no_results, searchText ?: "your search"),
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
    )
}

@Composable
internal fun ShowRetryScreen(message: String?) {
    Text(
        text = if(message.isNullOrBlank()) stringResource(id = R.string.generic_error) else message,
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
internal fun PokemonSpriteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = stringResource(R.string.content_description_pokemon_image),
        modifier = modifier.wrapContentSize().testTag("PokemonSpriteImage")
    )
}


@Composable
internal fun PokemonDescriptionText(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .testTag("PokemonDescriptionText"),
            text = description,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}