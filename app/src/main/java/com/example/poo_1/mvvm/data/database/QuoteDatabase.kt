package com.example.poo_1.mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.poo_1.mvvm.data.database.dao.QuoteDao
import com.example.poo_1.mvvm.data.database.entities.QuoteEntity

//12:41

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
}