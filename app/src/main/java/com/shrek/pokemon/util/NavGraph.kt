package com.shrek.pokemon.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shrek.pokemon.MainViewModel
import com.shrek.pokemon.ui.MainScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(navController = navHostController, startDestination = "main") {
        composable("main") { MainScreen(mainViewModel) }
    }
}