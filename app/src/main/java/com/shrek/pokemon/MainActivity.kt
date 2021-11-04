package com.shrek.pokemon

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shrek.pokemon.util.getInjector


class MainActivity : AppCompatActivity() {

    private val injector by lazy { getInjector(this) }
    private val viewModel: MainViewModel by viewModels { injector.provideMainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}