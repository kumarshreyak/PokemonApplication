package com.shrek.pokemon.network.api

import android.util.Log
import com.shrek.pokemon.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private val gsonConverterFactory by lazy {
    GsonConverterFactory.create(retrofitGson)
}

private const val SERVER_URL_KEY = "server_url"
private const val QA_SERVER_URL_KEY = "qa_server_url"

private val API_BASE_URL: String = "something"

val TIMEOUT = 10L // 10s for now

private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(userAuthInterceptor)
            .build()
}

private val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) BODY else NONE)
}

// TODO update this accordingly later.
private val userAuthInterceptor by lazy {
    Interceptor { chain ->
        val request = chain.request()
        val updatedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer something")
                .build()
        chain.proceed(updatedRequest)
    }
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .also {
                Log.d("ShrekAPI", "created retrofit instance. baseUrl=$API_BASE_URL")
            }
}

inline fun <reified T> create(): T {
    return retrofit.create(T::class.java)
}