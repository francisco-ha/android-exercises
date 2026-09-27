package com.example.poo_1.flows.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityMainFlowsBinding
import kotlinx.coroutines.launch

class MainFlowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainFlowsBinding
    // Inicialización perezosa y segura del ViewModel vinculada al ciclo de vida de la pantalla
    // (evita pérdida de datos al rotar).
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFlowsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //viewModel.example()

        val progressBar = binding.progressBar
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect { uiState ->
                    when(uiState){
                        is MainUiState.Error -> {
                            progressBar.isVisible = false
                            Toast.makeText(this@MainFlowsActivity,"Error de: ${uiState.msg}", Toast.LENGTH_LONG).show()
                        }
                        MainUiState.Loading -> {
                            progressBar.isVisible = true
                        }
                        is MainUiState.Sucess -> {
                            progressBar.isVisible = false
                            Toast.makeText(this@MainFlowsActivity,"Num = ${uiState.numSubcribers}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        viewModel.example2()
    }
}