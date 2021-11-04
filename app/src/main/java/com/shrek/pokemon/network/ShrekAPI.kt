package com.shrek.pokemon.network

import com.shrek.pokemon.network.service.MainService
import com.shrek.pokemon.network.api.retrofit

// All Retrofit interfaces / APIs here
object ShrekAPI {
    val mainInterface: MainService by lazy { create<MainService>() }
}

inline fun <reified T> create(): T {
    return retrofit.create(T::class.java)
}