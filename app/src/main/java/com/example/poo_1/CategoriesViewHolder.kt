package com.example.poo_1

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)


    fun render(taskCategory: TaskCategory){
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_business_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_personal_category)
                )
            }
            TaskCategory.Tasks -> {
                tvCategoryName.text = "Tareas"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_other_category)
                )
            }
            else ->
                tvCategoryName.text = "Otros"
                //divider.setBackgroundColor(
                //ContextCompat.getColor(divider.context,R.color.todo_business_category)
                //)

        }
    }
}