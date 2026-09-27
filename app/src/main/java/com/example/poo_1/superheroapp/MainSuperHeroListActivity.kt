package com.example.poo_1.superheroapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poo_1.R
import com.example.poo_1.databinding.ActivityMainSuperHeroListBinding
import com.example.poo_1.superheroapp.DetailSuperHeroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainSuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSuperHeroListBinding
    private lateinit var pokemonRetrofit: Retrofit
    private lateinit var rickAndMortyRetrofit: Retrofit
    private lateinit var superHeroAdapter: SuperHeroAdapter

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainSuperHeroListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_super_hero_list)
        //rvSuperHero = findViewById(R.id.rvSuperHero)
        setContentView(binding.root)
        pokemonRetrofit=getRetrofitPokemon()
        rickAndMortyRetrofit=getRetrofitRickAndMorty()
        initUi()
        configSwipe()

    }

    private fun configSwipe() {
        binding.swipe.isEnabled = true // con esto habilitamos o deshabilitamos la funcionalidad

        //los colores cambiaran segun pase el tiempo
        binding.swipe.setColorSchemeResources(R.color.red,R.color.purple,R.color.orange)
        //fondo del color
        binding.swipe.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this,R.color.teal_200))

        binding.swipe.setOnRefreshListener {
            //binding.swipe.isRefreshing = false
            Handler(Looper.getMainLooper()).postDelayed({
                //Lo que hay aqui se hara despues de pasados los "delayMillis"
                binding.swipe.isRefreshing = false
            },3000)
        }
    }

    private fun initUi(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName2(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.btnAddNewItem.setOnClickListener { Toast.makeText(this,"Boton en desarrollo",Toast.LENGTH_SHORT).show() }
        /*
            fun setUpRecyclerView(){
                mRecyclerView = findViewById(R.id.rvSuperheroList) as RecyclerView
                mRecyclerView.setHasFixedSize(true)
                mRecyclerView.layoutManager = LinearLayoutManager(this)
                mAdapter.RecyclerAdapter(getSuperheros(), this)
                mRecyclerView.adapter = mAdapter
            }
         */

        //SuperHeroAdapter { it: String -> navigateToDetail(it) } it es el id que recuperamos y enviaremos a la funcion navigateToDetail
        //superHeroAdapter = SuperHeroAdapter(SuperHeroProvider.superHeroList,{ superHeroId -> navigateToDetail(superHeroId)})
        //superHeroAdapter = SuperHeroAdapter(){navigateToDetail(it)}
        superHeroAdapter = SuperHeroAdapter{superHeroId -> navigateToDetail(superHeroId)}
            // Le dice al sistema que el tamaño (ancho y alto) del RecyclerView no va a cambiar, sin importar si agregas o eliminas elementos de la lista.
        binding.rvSuperHero.setHasFixedSize(true)

        //LinearLayoutManager(context, orientation, reverseLayout)
        //Define cómo se van a ordenar visualmente los elementos. (lista vertical estándar)
        //binding.rvSuperHero.layoutManager = GridLayoutManager(context,2) //para poner en columnas de 2
        layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.layoutManager = layoutManager
        //Los elementos se desplazan de izquierda a derecha
        //binding.rvSuperHero.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //binding.rvSuperHero.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)

        binding.rvSuperHero.adapter = superHeroAdapter
    }

    private fun searchByName(query: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<PokemonCharacterDataResponse> = pokemonRetrofit
                .create(ApiService::class.java).getPokeCharacter(query)

            if (myResponse.isSuccessful) {
                val response: PokemonCharacterDataResponse? = myResponse.body()

                if (response != null) {
                    Log.d("API_INFO", response.toString())
                }
            } else {
                Log.e("API_INFO", "Error al buscar el personaje: ${myResponse.code()}")
            }

        }
    }
    private fun searchByName2(query: String?) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<RickAndMortyCharacterDataResponse> = rickAndMortyRetrofit
                .create(ApiService::class.java).getRickCharacter(query)

            if (myResponse.isSuccessful) {
                val response: RickAndMortyCharacterDataResponse? = myResponse.body()
                Log.d("API_INFO", "Si funciona")
                if (response != null) {
                    Log.i("API_INFO", response.toString())
                    runOnUiThread {
                        superHeroAdapter.updateList(response.personajes)
                        // Pasa al hilo principal para modificar la pantalla de forma segura y evitar que la app explote
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.e("API_INFO", "Error al buscar el personaje: ${myResponse.code()}")
            }

        }
    }
    private fun getRetrofitPokemon():Retrofit{
        val pokemonRetrofit = Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return pokemonRetrofit
    }
    private fun getRetrofitRickAndMorty():Retrofit{
        val rickAndMortyRetrofit = Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return rickAndMortyRetrofit
    }
    private fun navigateToDetail(id: String){
        val intent = Intent(this,DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
    private fun addNewItem(){
        //crear el objeto
        /*val superHero = characterRMItemResponse(
            id = "Incognito",
            name = "AristiDevsCorporation",
            gender = "???????",
            uriImage = "https://pbs.twimg.com/profile_images/1037281659727634432/5x2XVPwB_400x400.jpg"
        )*/
        //modificar a que el adapter reciba un mutablelist
        //añadir en posicion
        //notificar al adapter superHeroAdapter.notifyItemInserted(3)
        //nos movemos si queremos a la posision insertada con un margen de 20
        //layoutManager.scrollToPositionWithOffset(3,20)
    }

    /*
    private fun funcionParaFiltrar(){
        binding.etFilter.addTextChangeListener{ userFilter->
            miLista.filter{ superhero ->
                val superHeroesFiltrados =
                superhero.superhero.lowercase().contains(userFilter.toString().lowercase)

                ***Importante
                android:imeOptions="actionDone"
            }
        }
     */
}