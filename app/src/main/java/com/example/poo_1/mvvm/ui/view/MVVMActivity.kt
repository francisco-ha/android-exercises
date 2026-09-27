package com.example.poo_1.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.poo_1.databinding.ActivityMvvmactivityBinding
import com.example.poo_1.mvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

// 1. Le avisas a Hilt que esta pantalla recibirá dependencias
// con AndroidEntryPoint
@AndroidEntryPoint
class MVVMActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMvvmactivityBinding

    // 'by viewModels()' es un "asistente" automático de Android:
    // Crea el ViewModel de forma segura y evita que tus datos se borren al girar la pantalla.
    // Además, es "perezoso" (lazy): no gasta memoria creando el objeto hasta que realmente lo usas.
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //----------
        quoteViewModel.alCrear()
        //----------

        quoteViewModel.quoteModel.observe(this, Observer {currentQuote ->
            /* Todo lo que este aqui dentro estara enganchado al LiveData y cuando haya un cambio
            se ejecutara lo que ponga aqui.
             */
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        quoteViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}