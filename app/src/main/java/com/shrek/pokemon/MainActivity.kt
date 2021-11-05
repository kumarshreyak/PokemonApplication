package com.shrek.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.shrek.pokemon.ui.theme.MyApplicationTheme
import com.shrek.pokemon.util.SetupNavGraph
import com.shrek.pokemon.util.getInjector

class MainActivity : ComponentActivity() {

    private val injector by lazy { getInjector(this) }
    private val viewModel: MainViewModel by viewModels { injector.provideMainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navHostController = rememberNavController()
                SetupNavGraph(
                    lifecycleOwner = this,
                    navHostController = navHostController,
                    mainViewModel = viewModel,
                )
            }
        }
    }
}

