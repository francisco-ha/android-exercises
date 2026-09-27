package com.example.poo_1.mvvm.data.domain

import com.example.poo_1.mvvm.data.QuoteRepository
import com.example.poo_1.mvvm.data.domain.model.Quote
import com.example.poo_1.mvvm.data.model.QuoteModel
import com.example.poo_1.mvvm.data.model.QuoteProvider
import javax.inject.Inject

class GetRamdomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository,
    //private val quoteProvider: QuoteProvider
    ){

    suspend operator fun invoke(): Quote? {
        //val quotes = QuoteProvider.quotes
        val quotes = repository.getAllQuotesFromDataBase()
        if (!quotes.isNullOrEmpty()){
            val position = (quotes.indices).random()
            return quotes[position]
        }
        return null
    }

}