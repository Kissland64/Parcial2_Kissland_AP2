package com.ucne.parcial2_kissland_ap2.di

import android.content.Context
import androidx.room.Room
import com.ucne.parcial2_kissland_ap2.data.database.SistemaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesSistemaDatabase(@ApplicationContext appContext: Context): SistemaDatabase =
        Room.databaseBuilder(appContext, SistemaDatabase::class.java, "Sistema.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSistemaDao(db: SistemaDatabase) = db.sistemaDao()
}