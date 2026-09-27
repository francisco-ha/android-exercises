package com.example.poo_1.flows.ui

sealed class MainUiState {
    object Loading: MainUiState()
    data class Sucess(val numSubcribers: Int): MainUiState()
    data class Error(val msg:String): MainUiState()
}