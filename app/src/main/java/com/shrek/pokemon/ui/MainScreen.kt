package com.shrek.pokemon.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asFlow
import coil.compose.rememberImagePainter
import com.shrek.pokemon.MainViewModel
import com.shrek.pokemon.R
import com.shrek.pokemonlibrary.network.data.models.PokemonShakespeareDescription
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult
import kotlinx.coroutines.flow.debounce

const val DELAY_SEARCH_IN_MILLIS = 300L
const val MIN_CHARS_FOR_SEARCH = 1

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner,
) {
    val scope = rememberCoroutineScope() // Use to make API call during retry.
    val pokemonSearchResult by mainViewModel.searchResult.observeAsState()

    val searchText by mainViewModel.enteredSearchText.asFlow().debounce(DELAY_SEARCH_IN_MILLIS)
        .collectAsState(pokemonSearchResult?.result?.name ?: "")

    if(!searchText.isNullOrBlank() && searchText.length > MIN_CHARS_FOR_SEARCH)
        LaunchedEffect(key1 = searchText) {
            mainViewModel.searchPokemon(searchText = searchText!!)
        }

    Content(
        searchText = searchText,
        response = pokemonSearchResult,
        onSearch = {
            mainViewModel.enteredSearchText.value = it
        }
    )
}

@Composable
fun Content(searchText: String?, response: PokemonApiResult<PokemonShakespeareDescription>?, onSearch: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {

            Spacer(modifier = Modifier.size(16.dp))

            // Page Title
            Text(
                style = MaterialTheme.typography.h4,
                text = stringResource(R.string.page_title),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // Text input field
            var enteredText by rememberSaveable { mutableStateOf("") }
            var errorText by rememberSaveable { mutableStateOf("") }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally),
                value = enteredText,
                onValueChange = {
                    enteredText = it
                    errorText = ""
                    onSearch(it)
                },
                keyboardActions = KeyboardActions { errorText = validate(enteredText) },
                isError = errorText.isNotBlank(),
                placeholder = { Text(stringResource(R.string.search_pokemon)) },
                singleLine = true,
            )

            // Helper text if error exists
            if(errorText.isNotBlank())
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorText,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.caption,
                )

            Spacer(modifier = Modifier.size(16.dp))

            // FIXME - some intermeditate states show somehow, RCA and fix that.
            // Search Result Section
            when {
                response == null || searchText.isNullOrBlank() -> Unit // TODO think and handle this; probably as error
                response.isInProgress() || enteredText != searchText -> {
                    Log.d("PokemonLogs", "CircularProgressIndicator: searchText = $searchText, enteredText = $enteredText")
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = MaterialTheme.colors.primary
                    )
                }
                response.isSuccess() && response.result != null -> ResultSection(response.result!!)
                response.isError() -> {
                    if(response.error?.httpFailureCode == 404) {
                        Log.d("PokemonLogs", "NoResultsText: searchText = $searchText, enteredText = $enteredText")
                        NoResultsText(searchText = searchText)
                    } // Result not found
                    else {
                        Log.d("PokemonLogs", "ShowRetryScreen: searchText = $searchText, enteredText = $enteredText")
                        ShowRetryScreen(message = response.error?.errorMessage)
                    } // Retryable error
                }
            }
        }
    }
}

@Composable
fun ResultSection(pokemonShakespeareDescription: PokemonShakespeareDescription) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(data = pokemonShakespeareDescription.imgUrl),
            contentDescription = stringResource(R.string.content_description_pokemon_image),
            modifier = Modifier.wrapContentSize(),
        )

        Spacer(modifier = Modifier.size(16.dp))

        // Search result
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = pokemonShakespeareDescription.description,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Composable
fun NoResultsText(searchText: String? = null) {
    // No Search results text
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.no_results, searchText ?: "your search"),
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
    )
}

/**
 * Validates enteredText and returns a user-friendly error if invalid
 * @param showBlankError - used to show blank text error when user intentionally searches using search button
 * @param enteredText - text entered by user
 */
fun validate(enteredText: String, showBlankError: Boolean = false): String {
    return when{
        enteredText.isBlank() && showBlankError -> "Required field"
        else -> ""
    }
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