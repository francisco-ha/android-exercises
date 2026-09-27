package com.example.poo_1.mvvm.di

import com.example.poo_1.mvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

// 👇 AGREGA ESTAS LÍNEAS AQUÍ ARRIBA
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitFirebase

//Nos proveera dependencias que no son tan faciles de proveer: de librerias, o de clases que
//contienen interfaces
@Module //los module son lo que proveen dependencias
// 2. @InstallIn le dice a Hilt cuánto tiempo debe vivir esta fábrica en memoria.
// Al usar 'SingletonComponent', la fábrica existirá durante TODO el ciclo de vida de la aplicación.
@InstallIn(SingletonComponent::class) //activity
object NetworkModule {

    @RetrofitFirebase //<- es tu etiqueta personalizada (tu Calificador / Qualifier).
    // 3. @Singleton le dice a Hilt: "Solo fabrica este objeto UNA sola vez".
    // Cada vez que un Repositorio o Servicio pida un Retrofit, Hilt le entregará la misma instancia existente.
    // Esto ahorra muchísima memoria en el teléfono.
    @Singleton
    // 4. @Provides le dice a Hilt: "Esta función de abajo sabe fabricar y devolver un objeto Retrofit".
    // Cuando Hilt necesite un Retrofit en cualquier parte del código, ejecutará
    @Provides
    fun provideRetrofitWithInjection(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Singleton
    @Provides
    // Al poner 'retrofit: Retrofit' aquí dentro, Hilt busca automáticamente la función de arriba,
    // toma el Retrofit que ya sabe fabricar, y se lo mete a esta función como ingrediente.
    fun provideQuoteApiClient( @RetrofitFirebase retrofit: Retrofit): QuoteApiClient {
        // Hilt automáticamente tomará el Retrofit de arriba, creará la interfaz y la tendrá lista
        return retrofit.create(QuoteApiClient::class.java)
    }
}
/**
 * di significa Dependency Injection (en español: Inyección de Dependencias).Es el nombre que se usa
 * por convención en la programación de software para la carpeta o paquete que contiene toda la
 * configuración de librerías como Dagger Hilt [].¿Para qué sirve esta carpeta en tu proyecto?
 * Dentro de esa carpeta se guardan los "módulos" (como tu NetworkModule.kt). Su único trabajo
 * es actuar como una fábrica de herramientas.
 */