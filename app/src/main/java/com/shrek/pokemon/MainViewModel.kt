package com.shrek.pokemon

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shrek.pokemon.network.repository.MainRepository
import com.shrek.pokemonlibrary.client.PokemonClient
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val enteredSearchText = MutableLiveData("")

    var searchResult = mutableStateOf<PokemonApiResult?>(null)
    suspend fun searchPokemon(searchText: String) {
        searchResult.value = PokemonClient.instance().searchPokemon(searchText = searchText)
    }

    class Factory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepository) as T
        }
    }
    companion object {
        const val TAG = "MainViewModel"
    }
}