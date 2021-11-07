package com.shrek.pokemonlibrary.network

import com.shrek.pokemonlibrary.network.service.MainService
import com.shrek.pokemonlibrary.network.api.retrofit

// All Retrofit interfaces / APIs here
object ShrekAPI {
    val mainInterface: MainService by lazy { create<MainService>() }
}

inline fun <reified T> create(): T {
    return retrofit.create(T::class.java)
}