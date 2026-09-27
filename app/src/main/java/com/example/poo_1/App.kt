package com.example.poo_1

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
//import com.example.poo_1.AppConstants.DEFAULT_DARK_MODE
import com.example.poo_1.settings.SettingsActivity
import com.example.poo_1.sharedPreferences.Prefs
import dagger.hilt.android.HiltAndroidApp

//**********************
import com.example.poo_1.settings.dataStore // <-- Importa la extensión creada en SettingsActivity.kt
import kotlinx.coroutines.flow.first // <-- Necesario para la función .first()
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//**************************
// 1. Esta anotación activa Dagger Hilt en todo el proyecto.
// Genera en segundo plano el contenedor global donde se guardarán todas tus dependencias.
@HiltAndroidApp
class App : Application() {

    // 2. Un "companion object" sirve para crear variables estáticas.
    // Esto significa que puedes acceder a ellas desde cualquier parte de tu app sin crear una nueva instancia.
    companion object {
        // 3. 'lateinit var' le dice a Kotlin: "sé que esta variable no puede ser nula,
        // pero la inicializaré un poco más tarde (en el onCreate)".
        // 'private set' permite que cualquiera lea los datos, pero solo esta clase 'App' pueda modificarlos.
        lateinit var prefs: Prefs
            private set

        const val IS_DARK_MODE_DEFAULT = false
    }

    // 4. Este método se ejecuta AUTOMÁTICAMENTE una sola vez cuando el usuario abre la app por primera vez,
    // incluso antes de que aparezca la primera pantalla (MainActivity).
    override fun onCreate() {
        super.onCreate()

        // 5. Inicializamos las SharedPreferences pasándole el contexto global de la aplicación.
        // Al hacerlo aquí, aseguramos que 'prefs' esté listo para usarse en cualquier Activity o Fragment.
        prefs = Prefs(applicationContext)

        // NUEVO: Inicializamos DataStore pasándole el contexto global
        //dataStorePrefs = DataStorePrefs(applicationContext)

        applySavedTheme()
    }

    private fun applySavedTheme() {
        // Usamos runBlocking y .first() solo al arrancar para leer el valor exacto de DataStore
        runBlocking {
            val isDarkMode = dataStore.data.map { preferences ->
                preferences[booleanPreferencesKey(SettingsActivity.KEY_DARK_MODE)] ?: IS_DARK_MODE_DEFAULT
            }.first()

            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}
/*
object AppConstants {
    const val IS_DARK_MODE_DEFAULT = false
}*/

/*

package com.example.poo_1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.poo_1.databinding.ActivitySplashBinding
import com.example.poo_1.settings.SettingsActivity
import com.example.poo_1.settings.dataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAppAndNavigate()
    }

    private fun initAppAndNavigate() {
        lifecycleScope.launch {
            // 1. Lectura asíncrona del tema desde DataStore
            val isDarkMode = dataStore.data.map { preferences ->
                preferences[booleanPreferencesKey(SettingsActivity.KEY_DARK_MODE)] ?: App.DEFAULT_DARK_MODE
            }.first()

            // 2. Aplicar la apariencia visual
            val nightMode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(nightMode)

            // 3. (Opcional) Espacio reservado para verificar sesión o Remote Config aquí

            // 4. Redirigir a MainActivity y remover Splash de la pila de navegación
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}**/

/*
<?xml version="1.0" encoding="utf-8"?>
<ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/ivLogo"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@mipmap/ic_launcher"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ProgressBar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/ivLogo" />

</ConstraintLayout>
 */

/*
<application
    android:name=".App"
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/Theme.POO_1">

    <!-- Splash Activity como punto de entrada -->
    <activity
        android:name=".SplashActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

    <!-- MainActivity sin intent-filter de arranque -->
    <activity
        android:name=".MainActivity"
        android:exported="false" />

    <activity
        android:name=".settings.SettingsActivity"
        android:exported="false" />

</application>
 */