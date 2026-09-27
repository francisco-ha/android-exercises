package com.example.poo_1.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.poo_1.mvvm.data.domain.model.Quote

// 1. @Entity le dice a Room que esta clase representa una TABLA en la base de datos local.
// El parámetro 'tableName' define que la tabla se llamará físicamente "quote_table".
@Entity(tableName = "quote_table")
data class QuoteEntity(

    // 2. @PrimaryKey define que esta variable será la LLAVE PRIMARIA (el identificador único de cada fila).
    // 'autoGenerate = true' hace que el ID aumente solo (1, 2, 3...) cada vez que guardas una nueva frase.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0, // Se le da un valor inicial de 0 por defecto para que Room lo autogenere.

    // @ColumnInfo define el nombre real que tendrá la columna dentro de la tabla de la base de datos.
    @ColumnInfo(name = "quote")
    val quote: String,

    @ColumnInfo(name = "author")
    val author: String
)

fun Quote.toDatabase() = QuoteEntity(quote= quote, author = author)