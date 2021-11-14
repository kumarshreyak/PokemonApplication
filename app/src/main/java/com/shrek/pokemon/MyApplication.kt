package com.shrek.pokemon

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPokemonClient()
    }

    private fun initPokemonClient() {
        PokemonClient.Builder(appContext = applicationContext).build()
    }
}