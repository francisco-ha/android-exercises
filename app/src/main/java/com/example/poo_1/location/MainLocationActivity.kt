package com.example.poo_1.location

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityMainLocationBinding
import kotlinx.coroutines.launch

class MainLocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainLocationBinding
    private val locationService: LocationService = LocationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLocationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnLocation.setOnClickListener {
            lifecycleScope.launch {
                val result = locationService.getUserLocation(this@MainLocationActivity)
                if (result!=null){
                    binding.tvLocation.text =
                        "Latitud ${result.latitude} y Longitud ${result.longitude}"
                        Log.i("localizacion","Latitud ${result.latitude} y Longitud ${result.longitude}")
                }
                else{
                    Log.i("localizacion","localizacion nula")
                }
            }
        }
    }
}