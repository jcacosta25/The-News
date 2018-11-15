package io.jcal.thenewsprovider.repository.api.factory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object ServiceFactory {

    private const val MAX_READ_TIME_OUT_SECONDS = 180L
    private const val MAX_CONNECTION_TIME_OUT_SECONDS = 60L

    fun <T> createRetrofitService(
        clazz: Class<T>,
        baseUrl: String,
        sdcInterceptor: Interceptor?
    ): T {
        val httpClientBuilder = OkHttpClient.Builder()
        if (sdcInterceptor != null) httpClientBuilder.addInterceptor(sdcInterceptor)

        val loggerInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.i("OkHttp $message") })
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = httpClientBuilder
            .addInterceptor(loggerInterceptor)
            .readTimeout(MAX_CONNECTION_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(MAX_READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(clazz)
    }
}