package com.example.poo_1.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/*
interface ApiService {
    @GET("api/breed/{breed}/images/random")
    suspend fun getCharacter(@Path("breed")
                                 caracterName: String?):Response<CharacterDataResponse>
}*/

interface ApiService {
    // Primera instrucción (URL fija con un parámetro en la ruta)
    // Reemplaza {pokemonName} con el texto que tú le pases
    @GET("pokemon/{pokemonName}")
    suspend fun getPokeCharacter(
        @Path("pokemonName") characterName: String?
    ): Response<PokemonCharacterDataResponse>

    // Segunda instrucción (Usa @Query para añadir "?name=texto" al final de la URL)
    @GET("character/")
    suspend fun getRickCharacter(
        @Query("name") name: String?
    ): Response<RickAndMortyCharacterDataResponse> // Nota: Cambia el tipo de respuesta según la estructura de esta API

    @GET("character/{characterId}")
    suspend fun getRickCharacterId(
        @Path("characterId") characterId: String?
    ): Response<RickAndMortyDetailCharacterDataResponse>
}