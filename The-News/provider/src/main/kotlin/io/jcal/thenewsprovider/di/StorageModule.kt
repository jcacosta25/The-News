package io.jcal.thenewsprovider.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.repository.db.AppDataBase
import io.jcal.thenewsprovider.repository.db.DATABASE_NAME
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()
}