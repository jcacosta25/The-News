package io.jcal.thenews.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.di.NetworkModule
import io.jcal.thenewsprovider.di.RepositoryModule
import io.jcal.thenewsprovider.di.StorageModule
import io.jcal.thenewsprovider.domain.executor.JobExecutor
import io.jcal.thenewsprovider.domain.executor.ThreadExecutor
import io.jcal.thenewsprovider.repository.db.DATABASE_NAME
import io.jcal.thenewsprovider.repository.db.DATABASE_NAME_PROPERTY
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, NetworkModule::class, StorageModule::class])
class NewsAppModule {
    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Named(DATABASE_NAME_PROPERTY)
    fun provideDatabaseName(): String = DATABASE_NAME
}