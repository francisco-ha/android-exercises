package com.example.poo_1.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity(), OnFragmentActionsListener{
    private lateinit var binding: ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRed.setOnClickListener { replaceFragment(RedFragment()) }
        binding.btnBlue.setOnClickListener { replaceFragment(BlueFragment()) }
    }
    //Pone el nuevo fragmento encima del existente sin borrarlo
    private fun loadFragment(fragment: Fragment) {
        // 1. Obtienes al "administrador" de fragmentos y le dices: "Voy a hacer un cambio"
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 2. Le dices EXACTAMENTE qué cambio hacer:
        // "Mete este fragmento dentro del contenedor visual que tiene la ID 'fragmentContainer'"
        fragmentTransaction.add(R.id.fragmentContainer, fragment)

        // 3. Confirmas el cambio: "¡Hazlo ya!" (Aplica la orden en pantalla)
        fragmentTransaction.commit()
    }
    //Elimina el fragmento actual del contenedor y coloca el nuevo.
    private fun replaceFragment(fragment: Fragment) {
        // 1. Inicia la transacción: Le avisas al administrador de fragmentos que vas a hacer un cambio
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 2. Reemplaza la vista: Borra el fragmento que esté actualmente en 'fragmentContainer'
        //    y coloca el nuevo fragmento en su lugar
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        // 3. Guarda el historial: Registra este cambio en la pila de navegación ("Back Stack")
        //    para que al presionar el botón "Atrás" del teléfono vuelva a la pantalla anterior
        fragmentTransaction.addToBackStack(null)
        // 4. Aplica el cambio: Confirma la orden para que la nueva pantalla se muestre en la App
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton() {
        Toast.makeText(this, "El botón ha sido pulsado", Toast.LENGTH_SHORT).show()
    }
}