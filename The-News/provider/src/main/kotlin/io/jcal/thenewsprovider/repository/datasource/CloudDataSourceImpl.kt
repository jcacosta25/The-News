package io.jcal.thenewsprovider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.repository.api.NewsService
import io.jcal.thenewsprovider.repository.api.factory.ApiResponse
import io.jcal.thenewsprovider.repository.api.model.ArticlesResponse
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(val api: NewsService) : CloudDataSource {
    override fun getTopHeadlines(country: String): LiveData<ApiResponse<ArticlesResponse>> =
        api.getTopHeadlines(country)
}