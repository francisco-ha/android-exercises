package com.example.poo_1.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.poo_1.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse:characterRMItemResponse, onItemSelected:(String) -> Unit){

        binding.tvSuperHeroName.text = superHeroItemResponse.name
        //para poner la imagen usare la libreria de "  PICASSO   "
        //nos permite cargar una imagen desde  urls /coil para compose se puede usar

        Picasso.get().load(superHeroItemResponse.uriImage).into(binding.ivSuperHero)
        binding.root.setOnClickListener{onItemSelected(superHeroItemResponse.id)}

        /*
        El String (El dato real): Es la información que el ViewHolder saca del ítem (el id del héroe) y
        se la entrega a la Activity. Ese es el dato que manejas, mandas en el Intent y usas para hacer peticiones.
     */
    }
}