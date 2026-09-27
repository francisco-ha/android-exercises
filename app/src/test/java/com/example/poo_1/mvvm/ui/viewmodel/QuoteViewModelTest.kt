package com.example.poo_1.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.poo_1.mvvm.data.domain.GetQuotesUseCase
import com.example.poo_1.mvvm.data.domain.GetRamdomQuoteUseCase
import com.example.poo_1.mvvm.data.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi //colocamos esto por que setMain y resetMain aparecia el warning de experimental
class QuoteViewModelTest {
    //Moqueamos lo que recivimos por parametros en la clase
    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRamdomQuoteUseCase: GetRamdomQuoteUseCase

    // Instancia real del ViewModel que vamos a probar
    private lateinit var quoteViewModel: QuoteViewModel

    /* Una regla es una funcion en el onBefore pero abstraida*/
    // Regla de Architecture Components: Obliga a LiveData a ejecutar sus tareas
    // de forma síncrona en el hilo de pruebas local (JVM) sin requerir el Android Main Thread.
    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        //Instanciamos el ViewModel pasándole las dependencias simuladas
        quoteViewModel = QuoteViewModel(getQuotesUseCase,getRamdomQuoteUseCase)
        /*Modificaremos el dispatcher que es quien se encarga de gestionar los hilos que usaran nuestras corrutinas*/
        /*
         * Reemplaza el hilo principal de Android (Dispatchers.Main) que no existe en JVM.
         * Dispatchers.Unconfined hace que las corrutinas dentro de viewModelScope se ejecuten
         * al instante, permitiendo hacer asserts en la línea inmediatamente siguiente.
         */
        Dispatchers.setMain(Dispatchers.Unconfined)

    }
    @After
    fun onAfter(){
        // Restablece el hilo principal de Corrutinas a su estado original al finalizar
        Dispatchers.resetMain()
    }

    /*
     * TEST 1 : Se usa 1 solo elemento para evitar fallos aleatorios.
     */
    // Usaremos runTest para ejecutar corrutinas de forma síncrona y optimizada en el entorno de pruebas
    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest {
        // Given (Usamos 1 solo elemento para asegurar determinismo)
        val quoteList = listOf(Quote("Quote del sistema", "Sistema"))
        coEvery { getQuotesUseCase() } returns quoteList

        // When
        quoteViewModel.alCrear()

        // Then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
        assert(quoteViewModel.isLoading.value == false)
    }

    /*
     * TEST 2: randomQuote devuelve una frase válida.
     */
    @Test
    fun `when ramdomQuoteUseCase return a quotes and set on the livedata`() = runTest {
        //Given
        val quote = Quote("Qoute del sistema", "Sistema")
        coEvery { getRamdomQuoteUseCase() } returns quote

        //When: Ejecutamos el método a probar
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
        assert(quoteViewModel.isLoading.value == false) // <-- AGREGAR ESTA LÍNEA
    }

    /*
     * TEST 3: Si devuelve null, se debe mantener la frase anterior.
     */
    @Test
    fun `when ramdomQuoteUseCase return null keep the last value`() = runTest {
        //Given
        val quote = Quote("Qoute del sistema", "Sistema")
        quoteViewModel.quoteModel.value = quote

        coEvery { getRamdomQuoteUseCase() } returns null

        //When: Ejecutamos el método a probar
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
        assert(quoteViewModel.isLoading.value == false)
    }

    /*
     * TEST 4: alCrear() con lista vacía.
     * Si no hay frases (sin internet y BD vacía), quoteModel debe ser null e isLoading false.
     */
    @Test
    fun `when getQuotesUseCase returns empty list, set fallback quote`() = runTest {

        val expectedFallback = Quote("Sin conexión y sin datos guardados", "Sistema")
        // Given
        coEvery { getQuotesUseCase() } returns emptyList()

        // When
        quoteViewModel.alCrear()

        // Then
        assert(quoteViewModel.quoteModel.value == expectedFallback)
        assert(quoteViewModel.isLoading.value == false)
    }

}