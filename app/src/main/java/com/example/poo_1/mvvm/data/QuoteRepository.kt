package com.example.poo_1.mvvm.data

import com.example.poo_1.mvvm.data.database.dao.QuoteDao
import com.example.poo_1.mvvm.data.database.entities.QuoteEntity
import com.example.poo_1.mvvm.data.domain.model.Quote
import com.example.poo_1.mvvm.data.domain.model.toDomain
import com.example.poo_1.mvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api:QuoteService,
    //private val quoteProvider: QuoteProvider
    private val quoteDao: QuoteDao
){
    suspend fun getAllQuotesFromApi(): List<Quote>{
        val response = api.getQuotes()

        return response.map { it.toDomain() }
    }
    suspend fun getAllQuotesFromDataBase(): List<Quote>{
        val response = quoteDao.getAllQuotes()

        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }

    /**
     * suspend fun getAllQuotesFromApi(): List<QuoteModel>{
     *         val response = api.getQuotes()
     *         quoteProvider.quotes = response
     *         return response
     *     }
     */
}