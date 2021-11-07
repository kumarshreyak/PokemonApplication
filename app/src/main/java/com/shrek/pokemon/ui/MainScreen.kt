package com.shrek.pokemon.ui

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
import coil.compose.rememberImagePainter
import com.shrek.pokemon.MainViewModel
import com.shrek.pokemon.R
import com.shrek.pokemon.network.api.ApiResult
import com.shrek.pokemonlibrary.network.data.models.Pokemon
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult

val DELAY_SEARCH_IN_MILLIS = 300L

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner,
) {
    val scope = rememberCoroutineScope() // Use to make API call during retry.
    val pokemonSearchResult by mainViewModel.searchResult.observeAsState()

    // FIXME - Add debounce/throttle in search
//    val searchText by mainViewModel.enteredSearchText.asFlow().debounce(DELAY_SEARCH_IN_MILLIS)
//        .collectAsState(response.result?.contents?.text ?: "")

    val searchText by mainViewModel.enteredSearchText.observeAsState()

    if(!searchText.isNullOrBlank())
        LaunchedEffect(key1 = "searchText") { // TODO remove quotes around searchText here
            mainViewModel.searchPokemon(searchText = searchText!!)
        }

    Content(
        response = pokemonSearchResult,
        onSearch = {
            mainViewModel.enteredSearchText.value = it
        }
    )
}

@Composable
fun Content(response: PokemonApiResult?, onSearch: (String) -> Unit) {
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
            var enteredText by rememberSaveable { mutableStateOf(response?.result?.name ?: "") }
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

            // FIXME - Progress state doesn't show when search text gets updated.
            // Search Result Section
            when {
                response == null -> Unit // TODO think and handle this; probably as error 
                response.isInProgress() -> CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.primary
                )
                response.isSuccess() && response.result != null -> ResultSection(response.result)
                response.isError() -> ShowRetryScreen(message = response.error?.errorMessage)
            }
        }
    }
}

@Composable
fun ResultSection(response: Pokemon?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if(response == null) {
            // No Search results text
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_results, response?.name ?: "entered text"),
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.subtitle1,
            )
        } else {
            Image(
                painter = rememberImagePainter(data = response.imgUrl),
                contentDescription = stringResource(R.string.content_description_pokemon_image),
                modifier = Modifier.wrapContentSize(),
            )

            Spacer(modifier = Modifier.size(16.dp))

            // Search result
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = response.description,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
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