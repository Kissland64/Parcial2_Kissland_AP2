package com.ucne.parcial2_kissland_ap2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("sistema_table")
data class SistemasEntity(
    @PrimaryKey(autoGenerate = true)
    val idSistema : Int = 0,
    val nombre: String = "",
)