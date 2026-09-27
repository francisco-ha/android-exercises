package com.example.poo_1.dataStrorePreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityDetailDataStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailDataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch (Dispatchers.IO){
            getUserProfile().collect {
                withContext(Dispatchers.Main){
                    binding.tvName.text = it.name
                    if(it.isVip){
                        binding.container.setBackgroundResource(R.color.superhero_stat_power)
                    }
                }
            }
        }

    }
    private fun getUserProfile() =
         dataStore.data.map { preferences ->
             UserProfile(
                 // CORRECCIÓN: Usamos las constantes DIRECTAMENTE dentro de los corchetes [ ]
                 name = preferences[NAME_KEY].orEmpty(),
                 isVip = preferences[VIP_KEY] ?: false
             )
             /*UserProfile(
                 name = preferences[stringPreferencesKey("name")].orEmpty(),
                 isVip = preferences[booleanPreferencesKey("vip")] ?: false
                 )*/
        }

}

data class UserProfile(val name: String, val isVip: Boolean)