package com.example.pokedexmultiplatform

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(private val pokedexService: PokedexService) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
        return withContext(Dispatchers.IO) {
            pokedexService.getPokedex()
        }
    }
}
