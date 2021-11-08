package com.shrek.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shrek.pokemon.network.repository.MainRepository
import com.shrek.pokemonlibrary.client.PokemonClient

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val enteredSearchText = MutableLiveData("")

    val searchResult = PokemonClient.instance().pokemonSearchResult
    fun searchPokemon(searchText: String) {
        PokemonClient.instance().searchPokemon(pokemonName = searchText)
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