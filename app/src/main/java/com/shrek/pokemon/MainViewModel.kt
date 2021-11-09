package com.shrek.pokemon

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shrek.pokemon.network.repository.MainRepository
import com.shrek.pokemonlibrary.client.PokemonClient
import com.shrek.pokemonlibrary.network.data.models.PokemonApiResult
import com.shrek.pokemonlibrary.network.data.models.PokemonSprite

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val enteredSearchText = MutableLiveData("")

    val description = MutableLiveData<PokemonApiResult<String>?>(null)
    val sprite = MutableLiveData<PokemonApiResult<PokemonSprite>?>(null)

    fun searchPokemon(searchText: String){
        val response1 = PokemonClient.instance().searchPokemonShakespeareDescription(pokemonName = searchText)
        val response2 = PokemonClient.instance().searchPokemonSprite(pokemonName = searchText)
        Log.d("PokemonLogs", "ViewModel-searchPokemon : ${response1.resultState.name} - ${response2.resultState.name}")
        description.value = response1
        sprite.value = response2
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