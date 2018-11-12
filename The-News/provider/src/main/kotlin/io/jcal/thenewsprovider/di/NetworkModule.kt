package io.jcal.thenewsprovider.di

import dagger.Module
import dagger.Provides
import io.jcal.thenewsprovider.repository.api.NewsService
import io.jcal.thenewsprovider.repository.api.factory.Interceptor
import io.jcal.thenewsprovider.repository.api.factory.ServiceFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNewsService(interceptor: Interceptor): NewsService =
        ServiceFactory.createRetrofitService(NewsService::class.java, NEWS_WEB_URL, interceptor)


    companion object {
        const val NEWS_WEB_URL = "https://newsapi.org/v2/"
    }
}