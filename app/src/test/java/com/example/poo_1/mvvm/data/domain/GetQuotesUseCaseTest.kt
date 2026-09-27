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

/*
En el primer test mockeamos el repositorio para que de una lista vacia
El segundo es cuando la lista no esta vacia
 */
class GetQuotesUseCaseTest {

    // Se mockea lo que se recibe por parametro
    //@Mocck -> Es mejor pero mas dificil
    @RelaxedMockK
    private lateinit var  quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)//con this decimos inicializate aqui
        // Instanciamos el UseCase inyectándole nuestro repositorio simulado
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    /*
     * PRUEBA 1: La API responde bien pero regresa una lista vacía.
     * Resultado esperado: No limpia ni inserta en la BD local, y consulta la BD para recuperar datos.
     */
    //usaremos runBlocking por que lanzaremos corrutinas
    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        /** los agregados son para ser mas rigurosos, no es necesario aqui pero hay flujos que si lo requiren
         * asi es como se configura @Mockk, pero nosostros utilizamos @RelaxedMockk*/
        val expectedDbList = listOf(Quote("Frase local guardada", "Autor Local")) //agregado para ser mas riguroso

        //Given
        //every para funcion normal, coEvery para corutinas
        //(coEvery)cuando se llame al mock que le digamos regresa lo que te digo
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList() //esto ocurre cuando...when
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns expectedDbList //agregado para ser mas riguroso

        //When
        val result = getQuotesUseCase() //que va a pasar cuando se llame eso esta en el then
        //se agrega val result para capturar la respuesta y ser mas riguroso, no es muy necesario

        //then
        //con exactly verificamos el numerdo de veces que se llama aunque no es obligatorio
        coVerify (exactly = 0){ quoteRepository.clearQuotes() } //agregado para ser mas riguroso
        coVerify (exactly = 0){ quoteRepository.insertQuotes(any()) } //agregado para ser mas riguroso
        coVerify (exactly = 1){ quoteRepository.getAllQuotesFromDataBase() }
        //Garantiza que la salida del UseCase coincida exactamente con lo guardado en BD
        assert(result == expectedDbList)//agregado

    }

    /*
     * PRUEBA 2: La API responde correctamente con una lista de frases.
     * Resultado esperado: Limpia la BD local, guarda las nuevas frases y retorna la lista recibida de la API.
     */
    @Test
    fun `when the api return something then get values from api`() = runBlocking {

        val myList = listOf(Quote("Sistema funcionando","sistema"))
        //Given
        //every para funcion normal, coEvery para corutinas
        //(coEvery)cuando se llame al mock que le digamos regresa lo que te digo
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList //esto ocurre cuando...when

        //When
        val respuesta = getQuotesUseCase() //que va a pasar cuando se llame eso esta en el then

        //then
        //con exactly verificamos el numerdo de veces que se llama aunque no es obligatorio
        coVerify (exactly = 1){ quoteRepository.clearQuotes() }
        coVerify (exactly = 1){ quoteRepository.insertQuotes(any()) }
        coVerify (exactly = 0){ quoteRepository.getAllQuotesFromDataBase() }
        assert(myList == respuesta)

    }

    /*
     * PRUEBA 3: La API lanza una excepción por falta de internet o error de servidor.
     * Resultado esperado: El try-catch captura el error y consulta la base de datos local en lugar de tronar.
     */
    @Test
    fun `when the api throws an exception then get values from database`() = runBlocking {
        // GIVEN
        val expectedDbList = listOf(Quote("Frase en modo offline", "Autor BD"))

        // Simulamos un fallo de conexión usando 'throws'
        coEvery { quoteRepository.getAllQuotesFromApi() } throws Exception("Network Failure")
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns expectedDbList

        // WHEN
        val result = getQuotesUseCase()

        // THEN
        coVerify(exactly = 0) { quoteRepository.clearQuotes() }
        coVerify(exactly = 0) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDataBase() }

        assert(result == expectedDbList)
    }

}