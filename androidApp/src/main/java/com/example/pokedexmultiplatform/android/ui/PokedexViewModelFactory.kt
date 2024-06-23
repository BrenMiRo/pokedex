package com.example.pokedexmultiplatform.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexmultiplatform.PokedexRepositoryImpl
import com.example.pokedexmultiplatform.PokedexService

class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = PokedexRepositoryImpl(PokedexService())

        return PokedexViewModel(pokedexRepository) as T
    }
}