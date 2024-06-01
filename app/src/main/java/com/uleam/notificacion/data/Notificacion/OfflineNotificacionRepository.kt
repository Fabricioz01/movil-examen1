package com.uleam.notificacion.data.Notificacion

import com.uleam.notificacion.data.Notificacion.interfaces.NotificacionDao
import com.uleam.notificacion.data.Notificacion.interfaces.NotificacionRepository
import com.uleam.notificacion.data.Vistas.Vistas
import kotlinx.coroutines.flow.Flow

 class OfflineNotificacionRepository(private val notificacionDao: NotificacionDao) :
    NotificacionRepository {

    override fun getAllNotificaciones(): Flow<List<Notificacion>> = notificacionDao.getAllNotificaciones()

    override fun getByNotificacion(id: Int): Flow<Notificacion?> = notificacionDao.getByNotificacion(id)

    override suspend fun insertNotificacion(notificacion: Vistas) = notificacionDao.insertNotificacion(notificacion)

    override suspend fun delete(notificacion: Notificacion) = notificacionDao.delete(notificacion)

    override suspend fun update(notificacion: Notificacion) = notificacionDao.update(notificacion)
}
