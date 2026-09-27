package com.example.poo_1.fragments2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.poo_1.R
import androidx.fragment.app.*
import com.example.poo_1.databinding.ActivityMainFragmentBinding
import com.example.poo_1.fragments2.FirstFragment.Companion.ADDRESS_BUNDLE
import com.example.poo_1.fragments2.FirstFragment.Companion.NAME_BUNDLE

class MainFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFragmentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        if (savedInstanceState == null){ // si la pantalla es nueva solamente
            // 💡 SOLUCIÓN: Usamos la fábrica 'newInstance' del Fragment
            //val fragmento = FirstFragment.newInstance("FranciscoDev", "calle 12, #17")


            //Creamos un objeto que ontine los valores en los fragment
            val bundle = bundleOf(NAME_BUNDLE to "FranciscoDev",
                ADDRESS_BUNDLE to "calle 12, #17")

            //le decimos al supportFragmentManager que haga un cambio
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FirstFragment>(binding.fragmentContainer.id, args = bundle)
                //add(R.id.fragmentContainer, fragmento)
            }

        }


    }
}