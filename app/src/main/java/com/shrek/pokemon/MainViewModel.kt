package com.shrek.pokemon

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import com.shrek.pokemon.network.api.ApiResult
import com.shrek.pokemon.network.data.request.GetShakespeareTextRequest
import com.shrek.pokemon.network.data.request.GetShakespeareTextResponse
import com.shrek.pokemon.network.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val enteredSearchText = MutableLiveData("")
//    val searchText = enteredSearchText.asFlow().debounce(DELAY_SEARCH_IN_MILLIS)
    val getShakespeareTextResponse = mutableStateOf(ApiResult<GetShakespeareTextResponse>())

    suspend fun getShakespeareText(text: String) {
        val response = mainRepository.getShakespeareText(GetShakespeareTextRequest(text = text))
        getShakespeareTextResponse.value = response
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