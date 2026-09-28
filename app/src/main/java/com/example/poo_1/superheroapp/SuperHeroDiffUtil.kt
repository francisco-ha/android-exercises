package com.example.poo_1.superheroapp

import androidx.recyclerview.widget.DiffUtil


/*
DiffUtil necesita trabajar con listas nuevas por lo que no funcionara con
mutableListOf ya que esta es una lista que mantiene su referencia es decir no es nueva
y diff Util compara entre dos listas, es decir fallara al ser la misma referencia y compararse entre si
este es un principio de inmutabilidad
En caso de trabajar con una mutableList no utilizar list.add() sino mas bien list = list.plus()
 */
class SuperHeroDiffUtil(
    private val oldList: List<characterRMItemResponse>,
    private val newList: List<characterRMItemResponse>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]== newList[newItemPosition]
    }

}