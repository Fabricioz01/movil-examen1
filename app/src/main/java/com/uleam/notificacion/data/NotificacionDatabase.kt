package com.uleam.notificacion.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.uleam.notificacion.data.Notificacion.Notificacion
import com.uleam.notificacion.data.Notificacion.interfaces.NotificacionDao
import com.uleam.notificacion.data.Vistas.Vistas
import com.uleam.notificacion.data.Vistas.interfaces.VistasDao

@Database(
    entities = [
        Notificacion::class,
        Vistas::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NotificacionDatabase : RoomDatabase() {

    abstract fun notificacionDao(): NotificacionDao
    abstract fun vistasDao(): VistasDao

    companion object {
        @Volatile
        private var INSTANCE: NotificacionDatabase? = null

        fun getDatabase(context: Context): NotificacionDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NotificacionDatabase::class.java,
                    "notificaciondb"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
