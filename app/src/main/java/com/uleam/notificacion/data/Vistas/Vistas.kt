package com.uleam.notificacion.data.Vistas


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.uleam.notificacion.data.Notificacion.Notificacion

@Entity(tableName = "Vistas",
    foreignKeys = [ForeignKey(
        entity = Notificacion::class,
        parentColumns = ["NotificacionID"],
        childColumns = ["NotificacionID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Vistas(
    @PrimaryKey(autoGenerate = true)
    val VistasID: Int = 0,
    val NotificacionID: Int,
    val Usuario: String,
    val FechaVista: String
)
