package io.jcal.thenewsprovider.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.domain.interactor.base.DeviceUtils
import io.jcal.thenewsprovider.repository.Repository
import io.jcal.thenewsprovider.repository.RepositoryImpl
import io.jcal.thenewsprovider.repository.api.NewsService
import io.jcal.thenewsprovider.repository.datasource.CloudDataSource
import io.jcal.thenewsprovider.repository.datasource.CloudDataSourceImpl
import io.jcal.thenewsprovider.repository.datasource.DiskDataSource
import io.jcal.thenewsprovider.repository.datasource.DiskDataSourceImpl
import io.jcal.thenewsprovider.repository.db.AppDataBase
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMusicDiskDataSource(dataBase: AppDataBase): DiskDataSource =
        DiskDataSourceImpl(dataBase)

    @Provides
    @Singleton
    fun provideCloudDataSource(api: NewsService): CloudDataSource =
        CloudDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideDeviceUtils(context: Context): DeviceUtils = DeviceUtils(context)

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository = repository
}