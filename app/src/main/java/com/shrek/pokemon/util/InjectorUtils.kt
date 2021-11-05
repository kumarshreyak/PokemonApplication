package com.shrek.pokemon.util

import android.content.Context
import com.shrek.pokemon.MainViewModel
import com.shrek.pokemon.network.repository.MainRepository

private lateinit var InjectorUtils: Injector

fun getInjector(context: Context): Injector {
    if (!::InjectorUtils.isInitialized) {
        InjectorUtils = Injector(context)
    }
    return InjectorUtils
}

class Injector(context: Context) {
    private val mainRepository by lazy { MainRepository }

    fun provideMainViewModel(): MainViewModel.Factory {
        return MainViewModel.Factory(mainRepository)
    }
}