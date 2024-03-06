package com.ucne.parcial2_kissland_ap2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.parcial2_kissland_ap2.data.dao.SistemaDao
import com.ucne.parcial2_kissland_ap2.data.entity.SistemaEntity


@Database(
    entities = [SistemaEntity::class],
    version = 1
)
abstract class SistemaDatabase: RoomDatabase(){

    abstract fun sistemaDao(): SistemaDao

}