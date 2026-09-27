package com.example.poo_1.superheroapp

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacterDataResponse(
    @SerializedName("results") val personajes:List<characterRMItemResponse>
)

data class characterRMItemResponse(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("gender") val gender:String,
    @SerializedName("image") val uriImage:String
)
