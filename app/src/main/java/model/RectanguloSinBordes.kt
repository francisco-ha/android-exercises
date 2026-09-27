package model

import android.graphics.Color

class RectanguloSinBordes(
    color:Int, ancho:Int, alto:Int):RectanguloConBordes(color,ancho,alto) {


    override fun cambiarColorBorde(nuevoColorBorde:Int){
        colorBorde = nuevoColorBorde
    }

    fun eliminarBordes(){
        colorBorde = Color.TRANSPARENT
    }
}