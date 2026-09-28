package com.example.poo_1.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.poo_1.R
/*
1. Constructor de SuperHeroAdapter: Se crea el objeto en memoria. Como no le has pasado datos todavía,
 superHeroList se inicializa como una lista vacía (emptyList()).getItemCount():
2. El RecyclerView le pregunta al adaptador cuántas filas debe dibujar. El método lee que tu lista está vacía y devuelve 0

3. getItemViewType(position = 0): El RecyclerView se prepara para construir la primera fila (posición 0).
Llama a este método para revisar el género del personaje y devuelve un número (ej. 0 para Male o 1 para Female).

4. onCreateViewHolder(viewType = 0 o 1): Android toma el número que devolvió el paso anterior, lo mete en el
 parámetro viewType, evalúa el when, infla el XML correspondiente (item_superhero o item_superhero_female)
  y crea la estructura del ViewHolder en memoria.

 5.onBindViewHolder(position = 0): Una vez que la celda física ya existe, el adaptador toma al primer personaje
  de la lista (superHeroList[0]) y manda llamar a tu función viewHolder.bind(item).

getItemCount() -> onCreateViewHolder() ->  onBindViewHolder()
 */

class SuperHeroAdapter(
    var superHeroList:List<characterRMItemResponse> = emptyList(),
    private val onItemSelected:(String) -> Unit):
    RecyclerView.Adapter<SuperHeroViewHolder>(){

    //private val nombreVariable:(parametroEntrada) -> funcion
    //  -> el operador flecha indica el tipo de retorno en este caso una funcion que no hace nada


    /*
    El String (El dato real): Es la información que el ViewHolder saca del ítem (el id del héroe) y
    se la entrega a la Activity. Ese es el dato que manejas, mandas en el Intent y usas para hacer peticiones.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superhero,parent,false)
        return SuperHeroViewHolder(view)
    }

    //override fun getItemCount() = superHeroList.size
    override fun getItemCount(): Int {
        return superHeroList.size
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        viewHolder.bind(item,onItemSelected)
    }

    fun updateList(superHeroList: List<characterRMItemResponse>){
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }
    fun updateListWithDiffUtil(newList: List<characterRMItemResponse>){
        val superHeroDiff = SuperHeroDiffUtil(superHeroList,newList)
        val result = DiffUtil.calculateDiff(superHeroDiff)
        this.superHeroList = newList
        result.dispatchUpdatesTo(this)
    }


    /*

    companion object {
        private const val TYPE_MALE = 0
        private const val TYPE_FEMALE = 1
    }

    // 2. El método que analiza los datos y le asigna un tipo numérico a cada fila
    override fun getItemViewType(position: Int): Int {
        val character = superHeroList[position]
        // Comparamos el género que viene de tu data class
        return if (character.gender.equals("Female", ignoreCase = true)) {
            TYPE_FEMALE
        } else {
            TYPE_MALE
        }
    }

    // 3. Aquí es donde se USA el parámetro viewType de forma obligatoria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        // Seleccionamos el archivo XML correcto dependiendo del tipo que calculó Android
        val layoutRes = when (viewType) {
            TYPE_FEMALE -> R.layout.item_superhero_female // Diseño especial para mujeres
            else -> R.layout.item_superhero               // Tu diseño estándar original
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return SuperHeroViewHolder(view)
    }

     */
}