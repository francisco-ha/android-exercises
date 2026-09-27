package com.example.poo_1.mvvm.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
    object define un Singleton de forma automática y nativa
 */
object RetrofitHelper {
    fun provideRetrofitWithOutInjection(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}