package com.uleam.notificacion.data.Vistas

import com.uleam.notificacion.data.Vistas.interfaces.VistasDao
import com.uleam.notificacion.data.Vistas.interfaces.VistasRepository


import kotlinx.coroutines.flow.Flow

class OfflineVistasRepository(private val vistasDao: VistasDao) : VistasRepository {
    override fun getAllVistas(): Flow<List<Vistas>> = vistasDao.getAllVistas()

    override fun getVistasByNotificacion(id: Int): Flow<Vistas?> = vistasDao.getVistasByNotificacion(id)

    override fun getVistasByNotificacionId(notificacionId: Int): Vistas? = vistasDao.getVistasByNotificacionId(notificacionId)

    override suspend fun insertVistas(vistas: Vistas) = vistasDao.insertVistas(vistas)

    override suspend fun delete(vistas: Vistas) = vistasDao.delete(vistas)

    override suspend fun update(vistas: Vistas) = vistasDao.update(vistas)
}
