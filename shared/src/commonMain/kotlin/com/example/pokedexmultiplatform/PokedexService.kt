package com.example.pokedexmultiplatform

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments

class PokedexService : KtorApi() {
    suspend fun getPokedex(): Pokedex {
        val response = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "pokeapi.co"
                appendPathSegments("api", "v2", "pokemon")
                //parameters["limit"] = "80"
            }
        }
        return response.body() as Pokedex
    }
}