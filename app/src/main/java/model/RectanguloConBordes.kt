package model

import android.graphics.Color

open class RectanguloConBordes(
    color:Int, ancho:Int, alto:Int, var colorBorde:Int = Color.BLACK
):Rectangulo(color,ancho,alto) {
    open fun cambiarColorBorde(nuevoColorBorde:Int){
        colorBorde = nuevoColorBorde
    }

    class ManejoColor{
        /**
         * objeto que se define dentro de una clase y cuyos miembros (propiedades y métodos) se vinculan
         * directamente a la clase en sí, en lugar de a sus instancias individuales.
         * Dado que Kotlin no tiene la palabra reservada static como Java o C#, el companion object
         * es el mecanismo idiomático diseñado para reemplazar el comportamiento estático
         * de esos lenguajes
         */
        companion object{
            val ROJO = Color.RED
            val AZUL = Color.BLUE
            val VERDE = Color.GREEN
            val NEGRO = Color.BLACK

            fun obtenerColorAleatorio():Int{
                val colores = listOf(ROJO,AZUL,VERDE,NEGRO)
                return colores.random()
            }
        }

    }
}