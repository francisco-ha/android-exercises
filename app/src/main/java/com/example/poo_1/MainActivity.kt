package com.example.poo_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poo_1.dataStrorePreferences.MainDataStoreActivity
import com.example.poo_1.databinding.ActivityMainBinding
import com.example.poo_1.datePicker.DateTimePickerActivity
import com.example.poo_1.fragments.FragmentsActivity
import com.example.poo_1.fragments2.MainFragmentActivity
import com.example.poo_1.imagePicker.MainImageActivity
import com.example.poo_1.mvvm.ui.view.MVVMActivity
import com.example.poo_1.settings.SettingsActivity
import com.example.poo_1.sharedPreferences.SharedPreferencesActivity
import com.example.poo_1.superheroapp.MainSuperHeroListActivity
import com.example.poo_1.permissions.PermissionActivity
import com.example.poo_1.scanCode.ScanCodeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReciclerView.setOnClickListener { navigateToReciclerViewApp() }
        binding.btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }
        binding.btnSettings.setOnClickListener { navigateToSettingsApp() }
        binding.btnSharedPreferences.setOnClickListener { navigateSharedPreferencesApp() }
        binding.btnPermissions.setOnClickListener { navigateToPermissionsApp() }
        binding.btnDateTimePicker.setOnClickListener { navigateToPickerApp() }
        binding.btnScanCode.setOnClickListener { navigateToScanApp() }
        binding.btnFragments.setOnClickListener { navigateToFragmentsApp() }
        binding.btnMVVM.setOnClickListener { navigateToMVVMApp() }
        binding.btnDataStore.setOnClickListener { navigateToDataStoreApp() }
        binding.btnPickImage.setOnClickListener { navigateToPickImage() }
        binding.btnFragments2.setOnClickListener { navigateToFragments2App() }

    }

    private fun navigateToFragments2App() {
        startActivity (Intent(this, MainFragmentActivity::class.java))
    }

    private fun navigateToPickImage() {
        startActivity(Intent(this, MainImageActivity::class.java))
    }

    private fun navigateToDataStoreApp() {
        startActivity(Intent(this, MainDataStoreActivity::class.java))
    }
    private fun navigateToReciclerViewApp(){
        startActivity(Intent(this, ReciclerViewActivity::class.java))
    }
    private fun navigateToSuperHeroApp(){
        startActivity( Intent(this, MainSuperHeroListActivity::class.java))
    }
    private fun navigateToSettingsApp(){
        startActivity( Intent(this, SettingsActivity::class.java))
    }
    private fun navigateSharedPreferencesApp() {
        startActivity(Intent(this, SharedPreferencesActivity::class.java))
    }
    private fun navigateToPermissionsApp() {
        startActivity(Intent(this, PermissionActivity::class.java))
    }
    private fun navigateToPickerApp() {
        startActivity(Intent(this, DateTimePickerActivity::class.java))
    }
    private fun navigateToScanApp() {
        startActivity (Intent(this, ScanCodeActivity::class.java))
    }

    private fun navigateToFragmentsApp() {
        startActivity (Intent(this, FragmentsActivity::class.java))
    }
    private fun navigateToMVVMApp() {
        startActivity (Intent(this, MVVMActivity::class.java))
    }

}



        //val rectanguloView: View = findViewById(R.id.rectangulo)
        /*
            Post, signifia espera un poco de que se contruya la interfaz o vista, esto para obtener las
            medidas
         */
        /*rectanguloView.post {
            val inicialX = rectanguloView.x.toInt()
            val inicialY = rectanguloView.y.toInt()
            val inicialAncho = rectanguloView.width
            val inicialAlto = rectanguloView.height
            /**ContextCompat: es una clase de utilidad para asegurar la compatibilidad en versinoes de android
             * proporciona una serie de metodos para acceder a recursos por ejemplo colores
             */
            /**
             * This hace referencia a la clase y contexto donde nos encontramos es decir el MAIN_ACTIVITY
             */
            /*
            var rectangulo: Rectangulo = Rectangulo(
                ContextCompat.getColor(this, R.color.red),
                inicialAncho, inicialAlto
            ).apply {
                x = inicialX
                y = inicialY
            }
            */
/*
            var rectangulo = RectanguloConBordes (
                ContextCompat.getColor(this, R.color.red),
                inicialAncho, inicialAlto
            ).apply {
                x = inicialX
                y = inicialY
                colorBorde = ContextCompat.getColor(this@MainActivity, R.color.black)
            }
*/
            var rectangulo = RectanguloSinBordes (
                ContextCompat.getColor(this, R.color.red),
                inicialAncho, inicialAlto
            ).apply {
                dimenciones.x = inicialX
                dimenciones.y = inicialY
            }
            //quitar los bordes
            rectangulo.eliminarBordes()
            actualizarVista(rectangulo,rectanguloView)

            val buttonArriba: Button = findViewById(R.id.buttonArriba)
            val buttonAbajo: Button = findViewById(R.id.buttonAbajo)
            val buttonIzquierda: Button = findViewById(R.id.buttonIzquierda)
            val buttonDerecha: Button = findViewById(R.id.buttonDerecha)
            val buttonTamano: Button = findViewById(R.id.buttonCambiarTamano)
            val buttonColor: Button = findViewById(R.id.buttonCambiarColor)
            val buttonColorBorde: Button = findViewById(R.id.buttonCambiarColorBorde)

            buttonArriba.setOnClickListener {
                rectangulo.moverArriba()
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonAbajo.setOnClickListener {
                rectangulo.moverAbajo()
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonIzquierda.setOnClickListener {
                rectangulo.moverIzquierda()
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonDerecha.setOnClickListener {
                rectangulo.moverDechecha()
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonTamano.setOnClickListener {
                rectangulo.cambiarTamaño(200, 200)
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonColor.setOnClickListener {
                //rectangulo.color = ContextCompat.getColor(this, R.color.blue)
                rectangulo.color = generarColorAleatorio();
                actualizarVista(rectangulo, rectanguloView)
            }
            buttonColorBorde.setOnClickListener {
                //rectangulo.color = ContextCompat.getColor(this, R.color.blue)
                //rectangulo.cambiarColorBorde(generarColorAleatorio())
                rectangulo.cambiarColorBorde(RectanguloConBordes.ManejoColor.obtenerColorAleatorio())
                actualizarVista(rectangulo, rectanguloView)
            }
        }
    }


    fun generarColorAleatorio():Int{
        val random = Random.Default
        var rojo = random.nextInt(256)
        var verde = random.nextInt(256)
        var azul = random.nextInt(256)
        return Color.rgb(rojo,verde,azul)
    }
    private fun actualizarVista (rectangulo:RectanguloConBordes, rectanguloView: View){
        val drawable= GradientDrawable()
        drawable.setColor(rectangulo.color)
        drawable.setStroke(10,rectangulo.colorBorde)

        rectanguloView.layoutParams.width = rectangulo.dimenciones.ancho
        rectanguloView.layoutParams.height = rectangulo.dimenciones.alto
        //rectanguloView.setBackgroundColor(rectangulo.color)
        rectanguloView.background = drawable
        rectanguloView.x = rectangulo.dimenciones.x.toFloat()
        rectanguloView.y = rectangulo.dimenciones.y.toFloat()

        rectanguloView.requestLayout()//con esta instruccion refrescamos la vista
    }

*/