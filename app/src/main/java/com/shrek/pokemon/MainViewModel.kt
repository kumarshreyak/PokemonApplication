package com.shrek.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shrek.pokemon.network.repository.MainRepository

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    class Factory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {


        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepository) as T
        }
    }
}