package com.shrek.pokemon.util

import android.content.Context
import com.shrek.pokemon.MainViewModel

private lateinit var InjectorUtils: Injector

fun getInjector(context: Context): Injector {
    if (!::InjectorUtils.isInitialized) {
        InjectorUtils = Injector(context)
    }
    return InjectorUtils
}

class Injector(context: Context) {

    fun provideMainViewModel(): MainViewModel.Factory {
        return MainViewModel.Factory()
    }
}