package com.example.poo_1.mvvm.data.domain

import com.example.poo_1.mvvm.data.QuoteRepository
import com.example.poo_1.mvvm.data.database.entities.toDatabase
import com.example.poo_1.mvvm.data.domain.model.Quote
import com.example.poo_1.mvvm.data.model.QuoteModel
import javax.inject.Inject
import kotlin.collections.List

/**
 * Al generar el test se debe de elegir donde crear es decir en carpeta test o androidTest
 */
class GetQuotesUseCase {

    // 1. Declaras la variable arriba (igual que en Java)
    private val repository: QuoteRepository

    // 2. Creas el constructor explícito donde Hilt inyectará el repositorio
    @Inject
    constructor(repository: QuoteRepository) {
        // 3. Asignas el valor manualmente dentro del cuerpo
        this.repository = repository
    }

    // Tu función para obtener la lista de citas
    suspend operator fun invoke(): List<Quote> {
        return try {
            val quotes = repository.getAllQuotesFromApi()
            if (quotes.isNotEmpty()) {
                repository.clearQuotes()
                repository.insertQuotes(quotes.map { it.toDatabase() })
                quotes
            } else {
                repository.getAllQuotesFromDataBase()
            }
        } catch (e: Exception) {
            // Si la red falla por falta de internet, salta aquí y recupera de Room
            repository.getAllQuotesFromDataBase()
        }
    }
}