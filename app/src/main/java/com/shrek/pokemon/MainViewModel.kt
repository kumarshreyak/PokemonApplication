package com.shrek.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel() : ViewModel() {

    val enteredSearchText = MutableLiveData("")

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
    companion object {
        const val TAG = "MainViewModel"
    }
}