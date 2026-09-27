package com.example.poo_1.mvvm.di

import android.content.Context
import androidx.room.Room
import com.example.poo_1.mvvm.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =//regresara un :QuoteDatabase
        // Room.databaseBuilder es la herramienta que arma la base de datos usando el contexto de la app,
        // la clase de tu base de datos (QuoteDatabase) y el nombre del archivo.
        Room.databaseBuilder(context, QuoteDatabase::class.java,QUOTE_DATABASE_NAME).build()

    // 5. Este método le dice a Hilt cómo conseguir el DAO.
    // Hilt es inteligente: ve que este método pide un 'db: QuoteDatabase', así que va al método de arriba,
    // toma la base de datos que ya fabricó y extrae el DAO de ella.
    @Singleton
    @Provides
    fun provideQuoteDao(db: QuoteDatabase) = db.getQuoteDao() //regresara un :QuoteDao
}


/**
 *     @Singleton
 *     @Provides
 *     fun provideRoom(@ApplicationContext context: Context): QuoteDatabase {
 *         // 1. Creamos el constructor/configurador de la base de datos
 *         val constructorDB = Room.databaseBuilder(
 *             context,
 *             QuoteDatabase::class.java,
 *             QUOTE_DATABASE_NAME
 *         )
 *
 *         // 2. Ejecutamos el método build() para que fabrique físicamente el objeto
 *         val baseDeDatosFabricada: QuoteDatabase = constructorDB.build()
 *
 *         // 3. Le devolvemos a Hilt la base de datos terminada
 *         return baseDeDatosFabricada
 *     }
 *
 *     @Singleton
 *     @Provides
 *     fun provideQuoteDao(db: QuoteDatabase): QuoteDao {
 *         // 1. Usamos la base de datos que Hilt nos pasó en el parámetro 'db'
 *         // y llamamos a su método interno para extraer el DAO.
 *         val daoExtraido: QuoteDao = db.getQuoteDao()
 *
 *         // 2. Le devolvemos a Hilt el DAO listo para ser usado en los repositorios
 *         return daoExtraido
 *     }
 *
 */