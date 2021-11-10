package com.shrek.pokemonlibrary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shrek.pokemonlibrary.client.PokemonClient
import com.shrek.pokemonlibrary.client.PokemonShakespeareDescriptionUI
import com.shrek.pokemonlibrary.client.theme.PokemonLibraryTheme
import com.shrek.pokemonlibrary.network.api.TIMEOUT
import com.shrek.pokemonlibrary.network.data.models.PokemonShakespeareDescription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PokemonUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()


    @Test
    fun testPokemonShakespeareDescriptionUI_NotFoundScenario() {
        composeTestRule.setContent {
            PokemonLibraryTheme { PokemonShakespeareDescriptionUI(searchText = "InvalidPokemonName") }
        }

        val client = PokemonClient.instance()
        while(client.descriptionResponse.value?.isInProgress() == true) Thread.sleep(100)

        assert(client.descriptionResponse.value?.isError() == true)
        if(client.descriptionResponse.value?.error?.httpFailureCode == 404)
            composeTestRule.onNodeWithText(text = "Couldn\'t find any results", substring = true).assertIsDisplayed()
        else
            composeTestRule.onNodeWithText(text = "Something went wrong", substring = true).assertIsDisplayed()
    }
}