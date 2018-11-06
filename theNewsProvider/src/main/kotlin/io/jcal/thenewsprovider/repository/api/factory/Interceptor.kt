package io.jcal.thenewsprovider.repository.api.factory

import io.jcal.thenewsprovider.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class Interceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(API_KEY, BuildConfig.ApiKey)
            .url(request.url().newBuilder().build())
            .build()
        return chain.proceed(request)
    }

    companion object {
        @JvmStatic
        private val API_KEY = "x-api-key"
    }
}