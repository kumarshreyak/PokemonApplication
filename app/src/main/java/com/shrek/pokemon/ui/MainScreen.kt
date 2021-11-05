package com.shrek.pokemon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.shrek.pokemon.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val scope = rememberCoroutineScope() // Use to make API call during retry.
    val translatedText by mainViewModel.shakespeareText.observeAsState()
    val response by mainViewModel.getShakespeareTextResponse

    LaunchedEffect(key1 = "key1") {
        mainViewModel.getShakespeareText(text = "the test")
    }
    when {
        response.isInProgress() -> Unit
        response.isSuccess() && (translatedText != null) -> Content(text = translatedText)
        response.isError() -> ShowRetryScreen(message = response.errorMessage ?: "")
    }

}

@Composable
fun Content(text: String?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting(text ?: "No translation found")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ShowRetryScreen(message: String) {
    Text(text = message)
}