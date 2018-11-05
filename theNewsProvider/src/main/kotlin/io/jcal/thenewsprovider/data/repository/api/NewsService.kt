package io.jcal.thenewsprovider.data.repository.api

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.data.repository.api.factory.ApiResponse
import io.jcal.thenewsprovider.data.repository.api.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String = "us"): LiveData<ApiResponse<TopHeadlinesResponse>>
}