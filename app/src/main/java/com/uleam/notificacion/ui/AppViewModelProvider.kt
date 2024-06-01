package com.uleam.notificacion.ui

import com.uleam.notificacion.NotificacionApplication
import com.uleam.notificacion.ui.notificacion.NotificacionViewModel
import com.uleam.notificacion.ui.vistas.VistasViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            VistasViewModel(
                NotificacionApplication().container.vistasRepository
            )
        }
        initializer {
            NotificacionViewModel(this.createSavedStateHandle(),
                NotificacionApplication().container.notificacionRepository
            )

        }
    }

    fun CreationExtras.NotificacionApplication(): NotificacionApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotificacionApplication)
}
