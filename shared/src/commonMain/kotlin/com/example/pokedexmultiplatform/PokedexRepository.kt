package com.example.pokedexmultiplatform

interface PokedexRepository {
    suspend fun getPokedex() : Pokedex
}