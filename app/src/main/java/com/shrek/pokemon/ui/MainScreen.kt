package com.shrek.pokemon.ui

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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.shrek.pokemon.MainViewModel
import com.shrek.pokemon.R
import com.shrek.pokemon.network.api.ApiResult
import com.shrek.pokemon.network.data.request.GetShakespeareTextResponse
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

val DELAY_SEARCH_IN_MILLIS = 300L

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner,
) {
    val scope = rememberCoroutineScope() // Use to make API call during retry.
    val response by mainViewModel.getShakespeareTextResponse
    val searchText by mainViewModel.enteredSearchText.asFlow().debounce(DELAY_SEARCH_IN_MILLIS).collectAsState("")

    LaunchedEffect(key1 = searchText) {
        mainViewModel.getShakespeareText(text = searchText)
    }
    Content(
        response = response,
        onSearch = {
            mainViewModel.enteredSearchText.value = it
        }
    )
}

@Composable
fun Content(response: ApiResult<GetShakespeareTextResponse>, onSearch: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.size(16.dp))

            // Page Title
            Text(
                style = MaterialTheme.typography.h3,
                text = stringResource(R.string.page_title),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // Text input field
            var enteredText by rememberSaveable { mutableStateOf("") }
            var errorText by rememberSaveable { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth()
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
                response.isInProgress() -> CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.primary
                )
                response.isSuccess() && response.result != null -> ResultSection(response.result)
                response.isError() -> ShowRetryScreen(message = response.errorMessage)
            }
        }
    }
}

@Composable
fun ResultSection(response: GetShakespeareTextResponse) {
    if(response.contents?.translated.isNullOrBlank()) {
        // No Search results text
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.no_results, response.contents?.text ?: "entered text"),
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.subtitle1,
        )
    } else {
        // Search result
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = response.contents?.translated!!,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.subtitle1,
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

@Composable
fun ShowRetryScreen(message: String?) {
    Text(
        text = if(message.isNullOrBlank()) stringResource(id = R.string.generic_error) else message,
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.fillMaxWidth()
    )
}