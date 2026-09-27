package com.example.poo_1.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.poo_1.App
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/*Funcion de extension permite a travez de un componente crear metodos*/
//el delegado by permite crear una unica instancia de la base de datos
val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name="settings")


class SettingsActivity : AppCompatActivity() {
    companion object{
        const val VOLUME_LVL="volume_lvl"
        const val KEY_BLUETOOTH="KEY_BLUETOOTH"
        const val KEY_VIBRATION="KEY_VIBRATION"
        const val KEY_DARK_MODE="KEY_DARK_MODE"

    }
    private lateinit var binding: ActivitySettingsBinding

    private var firstTime:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter{firstTime}.collect {settinsModel ->
                //datos SettingsModel()
                if (settinsModel!=null){
                    runOnUiThread {
                        binding.switchVibration.isChecked = settinsModel.vibration
                        binding.switchBluetooth.isChecked = settinsModel.bluetooth
                        binding.rsVolume.setValues(settinsModel.volume.toFloat())
                        binding.switchDarkMode.isChecked = settinsModel.darkMode
                        // 1. Solo actualizamos el estado visual del switch
                        binding.switchDarkMode.isChecked = settinsModel.darkMode

                        // 2. Conectamos el listener únicamente para clics futuros
                        setupDarkModeListener()

                        setupDarkModeListener()
                        firstTime= !firstTime
                    }
                }
            }
        }

    }
    private fun initUi(){
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch { saveVolume(value.toInt()) }
        }
        binding.switchBluetooth.setOnCheckedChangeListener { _, valueBoolean ->
            CoroutineScope(Dispatchers.IO).launch { saveOptions(KEY_BLUETOOTH, valueBoolean) }
        }
        binding.switchVibration.setOnCheckedChangeListener { _, valueBoolean ->
            CoroutineScope(Dispatchers.IO).launch { saveOptions(KEY_VIBRATION, valueBoolean) }
        }
        /*binding.switchDarkMode.setOnCheckedChangeListener { _ , valueBoleean ->
            if (valueBoleean){
                enableDarkMode()
            }
            else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions("KEY_DARK_MODE", valueBoleean)
            }
        }*/
    }
    private fun setupDarkModeListener() {
        binding.switchDarkMode.setOnCheckedChangeListener { _, valueBoolean ->
            if (valueBoolean) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, valueBoolean)
            }
        }
    }
    private suspend fun saveOptions(key:String,value: Boolean){
        dataStore.edit { preferences->
            preferences[booleanPreferencesKey(key)]=value
        }
    }
    private suspend fun saveVolume(value:Int){
        dataStore.edit {preferences ->
            //it[intPreferencesKey(VOLUME_LVL)]=value
            preferences[intPreferencesKey(VOLUME_LVL)]=value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map{preferences ->
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: App.IS_DARK_MODE_DEFAULT,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: false
            )
        }
    }
    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}