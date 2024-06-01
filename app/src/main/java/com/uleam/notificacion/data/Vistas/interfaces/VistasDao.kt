package com.uleam.notificacion.data.Vistas.interfaces

import com.uleam.notificacion.data.Vistas.Vistas


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VistasDao {
    @Insert
    suspend fun insertVistas(vistas: Vistas)

    @Update
    suspend fun update(vistas: Vistas)

    @Delete
    suspend fun delete(vistas: Vistas)

    @Query("SELECT * FROM Vistas")
    fun getAllVistas(): Flow<List<Vistas>>

    @Query("SELECT * FROM Vistas WHERE NotificacionID = :notificacionId")
    fun getVistasByNotificacionId(notificacionId: Int): Vistas?

    @Query("SELECT * FROM Vistas WHERE VistasID = :vistasID")
    fun getVistasByNotificacion(vistasID: Int): Flow<Vistas?>
}
