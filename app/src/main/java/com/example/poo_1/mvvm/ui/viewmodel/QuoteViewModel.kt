package com.example.poo_1.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poo_1.mvvm.data.domain.GetQuotesUseCase
import com.example.poo_1.mvvm.data.domain.GetRamdomQuoteUseCase
import com.example.poo_1.mvvm.data.domain.model.Quote
import com.example.poo_1.mvvm.data.model.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
Oye, esta clase no es normal. Conéctala con el sistema operativo Android para que mantenga su ciclo
 de vida especial, no se destruya al rotar la pantalla y se limpie de la memoria solo cuando la
 pantalla se cierre de verdad".
 */
// 1. Le dices a Hilt: "Esta clase es un ViewModel que vas a gestionar tú"
@HiltViewModel
class QuoteViewModel @Inject constructor(
    // 2. Le pides a Hilt las dependencias que necesitas en el constructor
    private val getQuotesUseCase:GetQuotesUseCase,
    private val getRamdomQuoteUseCase: GetRamdomQuoteUseCase
) : ViewModel(){

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()
    //var getQuotesUseCase = GetQuotesUseCase()
    //var getRamdomQuoteUseCase = GetRamdomQuoteUseCase()

    fun alCrear() {
        //permite crear una corrutina que se controla automaticamente
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()){
                val position = (result.indices).random()
                quoteModel.postValue(result[position])
            }
            else{
                quoteModel.postValue(Quote("Sin conexión y sin datos guardados", "Sistema"))
                // Sin internet y sin guardar en BD: la lista llega vacía sin tumbar la app
            }
            isLoading.postValue(false)
        }
    }

    /* RandomQuote sera llamada por la activity: cuando el usuario pulse la pantalla va a hacer un
        setclicklistener y nosotros desde el ativity llamaremos al view model y le diremos he pulsado
        la pantalla dame un quote ramdom
    */
    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val currentQuote = getRamdomQuoteUseCase()

            if (currentQuote != null) {
                //postValue es un método que se utiliza en Android para actualizar el valor de un LiveData
                // o MutableLiveData desde un hilo secundario (Background Thread) de forma segura.
                quoteModel.postValue(currentQuote!!)
            }


            /* Con hacer un cambio en el quoteModel por medio del LiveData avisara al activity de que
                han habido cambios y la actibity hara lo que crea conveniente
             */

            isLoading.postValue(false)
        }
    }

}

/**
 * @HiltViewModel
 * class QuoteViewModel : ViewModel {
 *
 *     // 1. Declaras las variables arriba (Igual que en Java)
 *     private val getQuotesUseCase: GetQuotesUseCase
 *     private val getRamdomQuoteUseCase: GetRamdomQuoteUseCase
 *
 *     val quoteModel = MutableLiveData<QuoteModel>()
 *     val isLoading = MutableLiveData<Boolean>()
 *
 *     // 2. Creas un constructor explícito usando la palabra 'constructor'
 *     // Aquí es donde Hilt inyecta los "ingredientes"
 *     @Inject
 *     constructor(
 *         getQuotesUseCase: GetQuotesUseCase,
 *         getRamdomQuoteUseCase: GetRamdomQuoteUseCase
 *     ) : super() { // El 'super()' equivale al super() de Java para llamar a la clase padre ViewModel
 *
 *         // 3. Asignas los valores manualmente dentro del cuerpo (Igual que el 'this.variable = ...' de Java)
 *         this.getQuotesUseCase = getQuotesUseCase
 *         this.getRamdomQuoteUseCase = getRamdomQuoteUseCase
 *     }
 * }
 */