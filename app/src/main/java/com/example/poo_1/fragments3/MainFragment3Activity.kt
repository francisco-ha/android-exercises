package com.example.poo_1.fragments3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.poo_1.R

/*
    Para el uso de la navegacion es importante agregar un directorio navigation en res
    ademas ahi se creara un Navigation Resource File el nombre ahi es a elejir
    ademas del pluging id("androidx.navigation.safeargs.kotlin") y la librerias
     implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    esto en el build.gradle.kts de la app
 */
class MainFragment3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_fragment3)
    }
}