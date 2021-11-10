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
import com.shrek.pokemonlibrary.client.ui.PokemonShakespeareDescriptionUI
import com.shrek.pokemonlibrary.client.ui.PokemonSpriteUI
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

const val DELAY_SEARCH_IN_MILLIS = 300L
const val MIN_CHARS_FOR_SEARCH = 1

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner,
) {
    val searchText by mainViewModel.enteredSearchText.asFlow().debounce(DELAY_SEARCH_IN_MILLIS)
        .collectAsState("")

    Content(
        mainViewModel = mainViewModel,
        searchText = searchText,
        onSearch = {
            mainViewModel.enteredSearchText.value = it
        }
    )
}

@Composable
fun Content(
    mainViewModel: MainViewModel,
    searchText: String?,
    onSearch: (String) -> Unit
) {
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

            TextFieldSection(columnScope = this, onSearch = onSearch)

            Spacer(modifier = Modifier.size(16.dp))

            PokemonSpriteUI(searchText = searchText)

            Spacer(modifier = Modifier.size(16.dp))

            PokemonShakespeareDescriptionUI(searchText = searchText)
        }
    }
}

@Composable
fun TextFieldSection(
    columnScope: ColumnScope,
    onSearch: (String) -> Unit
) {
    columnScope.apply {
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
        if(errorText.isNotBlank()) Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorText,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption,
            )
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