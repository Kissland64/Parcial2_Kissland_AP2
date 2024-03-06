package com.ucne.parcial2_kissland_ap2.data.repository

import com.ucne.parcial2_kissland_ap2.data.dao.SistemaDao
import com.ucne.parcial2_kissland_ap2.data.entity.SistemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun guardar(sistemaEntity: SistemaEntity) {
        sistemaDao.guardar(sistemaEntity)
    }

    suspend fun delete(sistemaEntity: SistemaEntity) {
        sistemaDao.delete(sistemaEntity)
    }

    fun getSistema(): Flow<List<SistemaEntity>> {
        return sistemaDao.getAll()
    }
}