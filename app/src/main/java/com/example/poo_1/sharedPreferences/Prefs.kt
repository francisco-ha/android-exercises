package com.example.poo_1.sharedPreferences

import android.content.Context

class Prefs (val context: Context){
    val SHARED_NAME = "mydtb"
    val SHARED_USER_NAME= "username"
    val SHARED_VIP= "vip"

    /*
    ---.getSharedPreferences(...): El método del sistema que busca o crea el archivo de preferencias.
    ---SHARED_NAME: Una constante de tipo String (ej. "my_app_prefs") que define el nombre del archivo
       XML físico donde se guardarán los datos de clave-valor.
    ---0 (Context.MODE_PRIVATE): El modo de acceso al archivo. El valor 0 indica que el archivo es
       totalmente privado y solo tu aplicación puede leerlo o escribirlo.
     */

    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun saveName(name: String){
        storage.edit().putString(SHARED_USER_NAME,name).apply()
    }

    fun saveVIP(vip: Boolean){
        storage.edit().putBoolean(SHARED_VIP,vip).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_USER_NAME,"")!!
    }
    fun getVIP():Boolean{
        return storage.getBoolean(SHARED_VIP,false)
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}