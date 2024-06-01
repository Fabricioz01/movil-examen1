package com.uleam.notificacion.data.Notificacion


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notificacion")
data class Notificacion(
    val Titulo: String,
    val Mensaje: String,
    val Fecha: String,
    val imageResId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var NotificacionID: Int = 0
}
