package com.uleam.notificacion


import android.app.Application

import com.uleam.notificacion.data.AppContainer
import com.uleam.notificacion.data.AppDataContainer

class NotificacionApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
