package io.jcal.thenews.di.component

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.jcal.thenews.NewsApp
import io.jcal.thenews.di.modules.NewsModule
import io.jcal.thenewsprovider.di.NetworkModule
import io.jcal.thenewsprovider.di.RepositoryModule
import io.jcal.thenewsprovider.di.StorageModule
import io.jcal.thenewsprovider.domain.executor.ThreadExecutor
import io.jcal.thenewsprovider.repository.api.NewsService
import io.jcal.thenewsprovider.repository.db.AppDataBase
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    NewsModule::class,
    RepositoryModule::class,
    StorageModule::class,
    NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun appDataBase(): AppDataBase

    fun newsApi(): NewsService

    fun resources(): Resources

    fun inject(application: NewsApp)

}