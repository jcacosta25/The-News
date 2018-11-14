package io.jcal.thenews.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.domain.executor.JobExecutor
import io.jcal.thenewsprovider.domain.executor.ThreadExecutor
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor


}