package com.shrek.pokemonlibrary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shrek.pokemonlibrary.client.PokemonClient
import com.shrek.pokemonlibrary.client.PokemonShakespeareDescriptionUI
import com.shrek.pokemonlibrary.client.PokemonSpriteUI
import com.shrek.pokemonlibrary.client.theme.PokemonLibraryTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    fun testPokemonShakespeareDescriptionUI_ErrorScenario() {
        composeTestRule.setContent {
            PokemonLibraryTheme { PokemonShakespeareDescriptionUI(searchText = "InvalidPokemonName") }
        }

        val client = PokemonClient.instance()
        while(client.descriptionResponse.value?.isInProgress() == true) Thread.sleep(100)

        assert(client.descriptionResponse.value?.isError() == true)
        if(client.descriptionResponse.value?.error?.httpFailureCode == 404)
            composeTestRule.onNodeWithText(text = "Couldn\'t find any results", substring = true).assertIsDisplayed()
        else
            composeTestRule.onNodeWithText(text = composeTestRule.activity.getString(R.string.generic_error), substring = true).assertIsDisplayed()
    }

    @Test
    fun testPokemonSpriteUI_ErrorScenario() {
        composeTestRule.setContent {
            PokemonLibraryTheme { PokemonSpriteUI(searchText = "InvalidPokemonName") }
        }

        val client = PokemonClient.instance()
        while(client.pokemonSpriteResponse.value?.isInProgress() == true) Thread.sleep(100)

        assert(client.pokemonSpriteResponse.value?.isError() == true)
        if(client.pokemonSpriteResponse.value?.error?.httpFailureCode == 404)
            composeTestRule.onNodeWithText(text = "Couldn\'t find any results", substring = true).assertIsDisplayed()
        else
            composeTestRule.onNodeWithText(text = composeTestRule.activity.getString(R.string.generic_error), substring = true).assertIsDisplayed()
    }

    /**
     * Might fail due to network constraints, API used here has rate limit of 5 calls per hour.
     */
    @Test
    fun testPokemonShakespeareDescriptionUI_SuccessScenario() {
        composeTestRule.setContent {
            PokemonLibraryTheme { PokemonShakespeareDescriptionUI(searchText = "Pikachu") }
        }

        val client = PokemonClient.instance()
        while(client.descriptionResponse.value?.isInProgress() == true) Thread.sleep(100)

        assert(client.descriptionResponse.value?.isSuccess() == true)
        composeTestRule.onNodeWithTag(testTag = "PokemonDescriptionText").assertIsDisplayed()
    }

    /**
     * Might fail due to network constraints, API used here has rate limit of 5 calls per hour.
     */
    @Test
    fun testPokemonSpriteUI_SuccessScenario() {
        composeTestRule.setContent {
            PokemonLibraryTheme { PokemonSpriteUI(searchText = "Pikachu") }
        }

        val client = PokemonClient.instance()
        while(client.pokemonSpriteResponse.value?.isInProgress() == true) Thread.sleep(100)

        assert(client.pokemonSpriteResponse.value?.isSuccess() == true)
        composeTestRule.onNodeWithContentDescription(
            label = composeTestRule.activity.getString(R.string.content_description_pokemon_image),
            substring = true
        ).assertIsDisplayed()
    }
}