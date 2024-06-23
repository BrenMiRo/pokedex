package com.example.pokedexmultiplatform.android.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexmultiplatform.Pokedex
import com.example.pokedexmultiplatform.PokedexRepository
import com.example.pokedexmultiplatform.android.PokedexScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(private val pokedexRepository: PokedexRepository) : ViewModel() {

    val pokedex = MutableLiveData<Pokedex>()

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading
    )
    val screenState: Flow<PokedexScreenState> = _screenState

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                pokedexRepository.getPokedex()
            }.onSuccess {
                pokedex.postValue(it)
                _screenState.value = PokedexScreenState.ShowPokedex(it)
            }.onFailure {
               Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                _screenState.value = PokedexScreenState.Error
            }.also { Log.d("BREN",it.toString()) }
        }
    }
}
