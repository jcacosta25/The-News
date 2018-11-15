package io.jcal.thenewsprovider.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.repository.db.AppDataBase
import io.jcal.thenewsprovider.repository.db.DATABASE_NAME
import io.jcal.thenewsprovider.repository.db.DATABASE_NAME_PROPERTY
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesDatabase(context: Context, @Named(DATABASE_NAME_PROPERTY) databaseName: String = DATABASE_NAME): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, databaseName).build()
}