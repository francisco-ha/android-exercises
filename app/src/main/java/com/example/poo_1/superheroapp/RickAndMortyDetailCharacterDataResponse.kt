package com.example.poo_1.superheroapp

import android.net.Uri
import com.google.gson.annotations.SerializedName // Se eliminó la llave errónea '}' que estaba aquí
import kotlin.random.Random

data class RickAndMortyDetailCharacterDataResponse(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("status") val status: String,
        @SerializedName("species") val species: String,
        @SerializedName("type") val type: String,
        @SerializedName("gender") val gender: String,
        @SerializedName("image") val imageUrl: String)
{
        // Al usar get(), obligamos a Kotlin a generar las estadísticas al vuelo.
        // Gson ya no puede meter un valor null aquí bajo ninguna circunstancia.
        val powerStats: PowerStatsResponse
                get() = PowerStatsResponse.generateRandom()
}
data class PowerStatsResponse(
        val intelligence: String,
        val strength: String,
        val speed: String,
        val durability: String,
        val power: String,
        val combat: String
) {
        companion object {
                // Función auxiliar para generar estadísticas aleatorias entre 1 y 100
                fun generateRandom(): PowerStatsResponse {
                        return PowerStatsResponse(
                                intelligence = Random.nextInt(1, 101).toString(),
                                strength = Random.nextInt(1, 101).toString(),
                                speed = Random.nextInt(1, 101).toString(),
                                durability = Random.nextInt(1, 101).toString(),
                                power = Random.nextInt(1, 101).toString(),
                                combat = Random.nextInt(1, 101).toString()
                        )
                }
        }
}
