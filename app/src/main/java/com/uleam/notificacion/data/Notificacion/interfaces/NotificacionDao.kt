package com.uleam.notificacion.data.Notificacion.interfaces


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.uleam.notificacion.data.Notificacion.Notificacion
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificacionDao {
    @Insert
    suspend fun insertNotificacion(notificacion: com.uleam.notificacion.data.Vistas.Vistas)

    @Update
    suspend fun update(notificacion: Notificacion)

    @Delete
    suspend fun delete(notificacion: Notificacion)

    @Query("SELECT * FROM Notificacion")
    fun getAllNotificaciones(): Flow<List<Notificacion>>

    @Query("SELECT * FROM Notificacion WHERE NotificacionID = :notificacionID")
    fun getByNotificacion(notificacionID: Int): Flow<Notificacion?>
}
