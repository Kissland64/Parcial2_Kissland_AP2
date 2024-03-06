package com.ucne.parcial2_kissland_ap2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.parcial2_kissland_ap2.data.entity.SistemaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SistemaDao {

    @Upsert
    suspend fun guardar(sistema: SistemaEntity)

    @Delete
    suspend fun delete(sistema: SistemaEntity)

    @Query("Select * From sistema_table")
    fun getAll(): Flow<List<SistemaEntity>>
}