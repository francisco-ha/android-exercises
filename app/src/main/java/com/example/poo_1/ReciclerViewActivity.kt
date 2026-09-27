package com.example.poo_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReciclerViewActivity : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Tasks,
        TaskCategory.Other
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler_view)
        initComponent()
        initUi()

        }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
    }
    private fun initUi() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(
            this,LinearLayoutManager.HORIZONTAL,false
        )
        rvCategories.adapter = categoriesAdapter
    }


}