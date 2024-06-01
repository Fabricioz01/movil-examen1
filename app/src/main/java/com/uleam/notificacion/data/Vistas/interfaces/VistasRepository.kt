package com.uleam.notificacion.data.Vistas.interfaces


import com.uleam.notificacion.data.Vistas.Vistas
import kotlinx.coroutines.flow.Flow

interface VistasRepository {

    fun getAllVistas(): Flow<List<Vistas>>

    fun getVistasByNotificacion(id: Int): Flow<Vistas?>

    fun getVistasByNotificacionId(notificacionId: Int): Vistas?

    suspend fun insertVistas(vistas: Vistas)

    suspend fun delete(vistas: Vistas)

    suspend fun update(vistas: Vistas)
}
