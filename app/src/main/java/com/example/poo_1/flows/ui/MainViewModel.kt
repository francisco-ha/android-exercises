package com.example.poo_1.flows.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poo_1.flows.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val repository = Repository()

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    fun example(){
        viewModelScope.launch {
            repository.counter
                .map{ it.toString()} //transforma el resultado
                .onEach { save(it) } //solo realiza acciones en el objeto a iterar
                .catch { error ->
                    Log.i("flow","Error ${error.message}")
                }
                .collect {
                    Log.i("flow", it)
            }
        }

    }

    fun example2(){
        viewModelScope.launch {
            repository.counter
                .catch { _uiState.value = MainUiState.Error(it.message.orEmpty()) }
                //indicamos donde ejecutar el hilo del flow (Solo lo que esta antes de ejecutara en el hilo indicado)
                .flowOn(Dispatchers.IO)
                .collect {
                    _uiState.value = MainUiState.Sucess(it)
                    Log.i("flow", it.toString())
                }
        }
    }

    private fun save(it: String) {}
}