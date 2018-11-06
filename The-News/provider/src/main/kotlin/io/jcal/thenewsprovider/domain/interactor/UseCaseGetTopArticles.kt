package io.jcal.thenewsprovider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.domain.executor.AppExecutors
import io.jcal.thenewsprovider.domain.interactor.base.DeviceUtils
import io.jcal.thenewsprovider.domain.interactor.base.NetworkBoundResource
import io.jcal.thenewsprovider.repository.Repository
import io.jcal.thenewsprovider.repository.mapper.model.ListArticlesModel
import javax.inject.Inject

class UseCaseGetTopArticles @Inject constructor(
    appExecutors: AppExecutors,
    private val repository: Repository,
    private val deviceUtils: DeviceUtils
) : NetworkBoundResource<ListArticlesModel>(appExecutors) {
    init {
        init(ListArticlesModel())
    }
    override fun saveCallResult(item: ListArticlesModel) {
        repository.insertArticles(item)
    }
    override fun shouldFetch(data: ListArticlesModel?): Boolean =
        deviceUtils.isNetworkAvailable

    override fun loadFromDb(): LiveData<ListArticlesModel> = repository.selectTopArticle()

    override fun createCall(): LiveData<ListArticlesModel> = repository.fetchTopArticles()
}