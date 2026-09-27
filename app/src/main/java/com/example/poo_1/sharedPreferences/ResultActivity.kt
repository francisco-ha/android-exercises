package com.example.poo_1.sharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityResultBinding
import com.example.poo_1.App.Companion.prefs

class ResultActivity : AppCompatActivity() {

    private lateinit var resultBinding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)
        initUi()
    }

    private fun initUi() {
        resultBinding.btnBack.setOnClickListener {
            prefs.wipe()
            // Solución: Usar el dispatcher moderno en lugar de onBackPressed()
            onBackPressedDispatcher.onBackPressed()
        }

        val userName = prefs.getName()
        resultBinding.tvName.text = "Bienvenido $userName"

        if (prefs.getVIP()) {
            setVIPColorBackground()
        }
    }

    private fun setVIPColorBackground() {
        resultBinding.container.setBackgroundColor(
            ContextCompat.getColor(this, R.color.superhero_stat_power)
        )
    }
}
