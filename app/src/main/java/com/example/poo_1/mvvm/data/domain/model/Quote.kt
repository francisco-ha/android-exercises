package com.example.poo_1.mvvm.data.domain.model

import com.example.poo_1.mvvm.data.database.entities.QuoteEntity
import com.example.poo_1.mvvm.data.model.QuoteModel

data class Quote( val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote,author)
fun QuoteEntity.toDomain() = Quote(quote,author)