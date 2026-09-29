package com.example.poo_1.fragments2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.example.poo_1.databinding.ActivityMainFragment2Binding
import com.example.poo_1.fragments2.First2Fragment.Companion.ADDRESS_BUNDLE
import com.example.poo_1.fragments2.First2Fragment.Companion.NAME_BUNDLE

/*  La Activity crea los datos y decide qué fragmento mostrar.
    El Fragment recibe esos datos, los lee para mostrar el Toast
    y dibuja la interfaz final en la pantalla.
 */
class MainFragment2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainFragment2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFragment2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        if (savedInstanceState == null){ // si la pantalla es nueva solamente
            // SOLUCIÓN: Usamos la fábrica 'newInstance' del Fragment
            //val fragmento = FirstFragment.newInstance("FranciscoDev", "calle 12, #17")


            //Creamos un objeto que ontine los valores en los fragment
            val bundle = bundleOf(NAME_BUNDLE to "FranciscoDev",
                ADDRESS_BUNDLE to "calle 12, #17")

            //le decimos al supportFragmentManager que haga un cambio
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<First2Fragment>(binding.fragmentContainer.id, args = bundle)
                //add(R.id.fragmentContainer, fragmento)
            }

        }


    }
}