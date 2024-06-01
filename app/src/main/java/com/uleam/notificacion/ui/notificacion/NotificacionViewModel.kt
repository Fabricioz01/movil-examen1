package com.uleam.notificacion.ui.notificacion

import com.uleam.notificacion.R
import com.uleam.notificacion.data.Notificacion.Notificacion
import com.uleam.notificacion.data.Notificacion.interfaces.NotificacionRepository


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uleam.notificacion.data.Vistas.Vistas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificacionViewModel(savedStateHandle: SavedStateHandle,
                            private val notificacionRepository: NotificacionRepository
): ViewModel() {

    companion object {
        private const val TAG = "NotificacionViewModel"
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val imageResIds = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image1,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )

    private fun getRandomImageResId(): Int {
        return imageResIds.random()
    }

    private fun getNotificaciones(): List<Vistas> {
        return listOf(
            Vistas(NotificacionID = 1, Usuario = "jony diaz", FechaVista = "Ya terminaste la tarea de moviles?"),
            Vistas(NotificacionID = 1, Usuario = "fabricio", FechaVista = "Por que me da error en el codigo de muestra...", ),
            Vistas(NotificacionID = 1, Usuario = "Debora Delgado", FechaVista = "Y eso es todo del chisme"),
            Vistas(NotificacionID = 1, Usuario = "Gregoryo", FechaVista = "Pasé bases de datos:)"),
            Vistas(NotificacionID = 1, Usuario = "Usuario 1", FechaVista = "2024-05-01"),
            Vistas(NotificacionID = 2, Usuario = "Usuario 2", FechaVista = "2024-05-02"),
            Vistas(NotificacionID = 3, Usuario = "Usuario 3", FechaVista = "2024-05-03"),
            Vistas(NotificacionID = 4, Usuario = "Usuario 4", FechaVista = "2024-05-04"),
            Vistas(NotificacionID = 5, Usuario = "Usuario 5", FechaVista = "2024-05-05"),
            Vistas(NotificacionID = 6, Usuario = "Usuario 6", FechaVista = "2024-05-06"),
            Vistas(NotificacionID = 7, Usuario = "Usuario 7", FechaVista = "2024-05-07"),
            Vistas(NotificacionID = 8, Usuario = "Usuario 8", FechaVista = "2024-05-08"),
            Vistas(NotificacionID = 9, Usuario = "Usuario 9", FechaVista = "2024-05-09"),
            Vistas(NotificacionID = 10, Usuario = "Usuario 10", FechaVista = "2024-05-10"),

        )
    }


    init {
        viewModelScope.launch {
            try {
                val notificacionesInDatabase = notificacionRepository.getAllNotificaciones().firstOrNull()
                if (notificacionesInDatabase == null) {
                    val exampleNotificaciones = getNotificaciones()
                    exampleNotificaciones.forEach { notificacion ->
                        notificacionRepository.insertNotificacion(notificacion)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing NotificacionViewModel: ${e.message}")
            }
        }
    }

    val notificacionUiState: StateFlow<NotificacionUiState> =
        notificacionRepository.getAllNotificaciones().map { notificaciones ->
            NotificacionUiState(notificaciones)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NotificacionUiState()
            )
}

data class NotificacionUiState(val notificacionList: List<Notificacion> = listOf())
