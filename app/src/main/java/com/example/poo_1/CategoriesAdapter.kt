package com.example.poo_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val categories: List<TaskCategory>)
    :RecyclerView.Adapter<CategoriesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task_category,parent,false)
        return CategoriesViewHolder(view)

    }

    override fun getItemCount(): Int {
        //return 2
        return categories.size

    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position])

    }
}