package model

open class Rectangulo(var color:Int, var ancho:Int, var alto:Int) {
    //coordenadas iniciales. Propiedades
    /*var x: Int=0;
    var y: Int=0;*/
    var dimenciones = MiDimencion(0,0,300,300)
    //Metodos de comportamientos para mover el rectangulo;
    fun moverArriba(){ dimenciones.y-=10 }
    fun moverAbajo(){ dimenciones.y+=10 }
    fun moverIzquierda(){ dimenciones.x-=10 }
    fun moverDechecha(){ dimenciones.x+=10 }
    fun cambiarTamaño(nuevoAncho:Int, nuevoAlto:Int){
        dimenciones.ancho=nuevoAncho
        dimenciones.alto=nuevoAlto

    }
}