package com.example.poo_1.mvvm.data.domain

import com.example.poo_1.mvvm.data.QuoteRepository
import com.example.poo_1.mvvm.data.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRamdomQuoteUseCaseTest {
    // Se mockea lo que se recibe por parametro
    //@Mocck -> Es mejor pero mas dificil
    @RelaxedMockK
    private lateinit var  quoteRepository: QuoteRepository

    lateinit var getRamdomQuoteUseCase: GetRamdomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)//con this decimos inicializate aqui
        // Instanciamos el UseCase inyectándole nuestro repositorio simulado
        getRamdomQuoteUseCase = GetRamdomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking{

        //Given
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns emptyList()
        //Then
        val response = getRamdomQuoteUseCase()
        //
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDataBase() }
        assert(response==null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking{

        val quoteList = listOf(Quote("Sistema funcionando","sistema"))
        //Given
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns quoteList

        //When
        val response = getRamdomQuoteUseCase()

        //Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDataBase() }
        assert(response == quoteList.first())
    }
}