package com.shrek.pokemonlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shrek.pokemonlibrary.client.PokemonClient

internal class TestActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PokemonClient.Builder(appContext = applicationContext).build()
    }
}

