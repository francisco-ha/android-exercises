package com.example.poo_1.dataStrorePreferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.poo_1.databinding.ActivityMainDataStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
Queremos que funcione desde el contexto.nombre
con el delegado "by" consegimos que sea un singleton es decir que haya unica instancia de la base de datos
 */
val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCE_NAME")

// 1. Define las llaves UNA SOLA VEZ a nivel de archivo para que ambas pantallas usen exactamente las mismas
val NAME_KEY = stringPreferencesKey("name")
val VIP_KEY = booleanPreferencesKey("vip")

class MainDataStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnContinue.setOnClickListener {

            val name = binding.etName.text.toString()
            val cbVip = binding.cbVip.isChecked
            /*se ejecute en un hilo secundario (de fondo) y que además esté vinculada al ciclo de
                vida de tu Activity o Fragment.
             */
            lifecycleScope.launch(Dispatchers.IO){
                saveValues(name,cbVip)
            }
            startActivity(Intent(this, DetailDataStoreActivity::class.java))
        }
    }

    private suspend fun saveValues(name: String, checked: Boolean) {
        dataStore.edit { preferences ->
            //TipodeValorPreferencesKey()
            preferences[NAME_KEY] = name
            preferences[VIP_KEY] = checked
        }
                       
    }
}