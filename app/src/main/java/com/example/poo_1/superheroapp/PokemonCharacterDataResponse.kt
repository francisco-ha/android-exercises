package com.example.poo_1.superheroapp

import com.google.gson.annotations.SerializedName

data class PokemonCharacterDataResponse(
    @SerializedName("id") val pokemonId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,

    // Entramos al bloque principal de imágenes
    @SerializedName("sprites") val sprites: SpriteContainer

    // Con esto le dices a Gson: "Toma todo el JSON raíz y mételo en este mapa"
    // Aunque en el JSON no exista la palabra "objetoCompleto", Gson mapeará la raíz aquí.
    //@SerializedName("") val objetoCompleto: Map<String, Any>
)

data class SpriteContainer(
    // Bajamos al bloque "other"
    @SerializedName("other") val otherImages: OtherContainer
)

data class OtherContainer(
    // Entramos a "official-artwork"
    @SerializedName("official-artwork") val officialArtwork: ArtworkContainer
)

data class ArtworkContainer(
    // Aquí está la URL final de la foto en HD
    @SerializedName("front_default") val highResImageUrl: String
)