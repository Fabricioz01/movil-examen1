package com.uleam.notificacion.data.Notificacion.interfaces


import com.uleam.notificacion.data.Notificacion.Notificacion
import com.uleam.notificacion.data.Vistas.Vistas
import kotlinx.coroutines.flow.Flow

interface NotificacionRepository {

    fun getAllNotificaciones(): Flow<List<Notificacion>>

    fun getByNotificacion(id: Int): Flow<Notificacion?>

    suspend fun insertNotificacion(notificacion: Vistas)

    suspend fun delete(notificacion: Notificacion)

    suspend fun update(notificacion: Notificacion)
}
