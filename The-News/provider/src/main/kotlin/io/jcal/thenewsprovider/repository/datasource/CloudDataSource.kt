package io.jcal.thenewsprovider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.repository.api.factory.ApiResponse
import io.jcal.thenewsprovider.repository.api.model.ArticlesResponse

interface CloudDataSource {
    fun getTopHeadlines(country: String = "us"): LiveData<ApiResponse<ArticlesResponse>>
}