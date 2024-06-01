package com.uleam.notificacion.data


import android.content.Context

import com.uleam.notificacion.data.Notificacion.OfflineNotificacionRepository
import com.uleam.notificacion.data.Notificacion.interfaces.NotificacionRepository
import com.uleam.notificacion.data.Vistas.OfflineVistasRepository
import com.uleam.notificacion.data.Vistas.interfaces.VistasRepository

interface AppContainer {
    val notificacionRepository: NotificacionRepository
    val vistasRepository: VistasRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val notificacionRepository: NotificacionRepository by lazy {
        OfflineNotificacionRepository(NotificacionDatabase.getDatabase(context).notificacionDao())
    }

    /**
     * Implementation for [VistasDao]
     */
    override val vistasRepository: VistasRepository by lazy {
        OfflineVistasRepository(NotificacionDatabase.getDatabase(context).vistasDao())
    }
}
