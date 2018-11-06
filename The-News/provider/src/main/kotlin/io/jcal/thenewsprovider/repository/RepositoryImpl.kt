package io.jcal.thenewsprovider.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import io.jcal.thenewsprovider.domain.interactor.base.DeviceUtils
import io.jcal.thenewsprovider.domain.interactor.base.NetworkErrorCodes.Companion.NETWORK_FAIL_ERROR
import io.jcal.thenewsprovider.domain.interactor.base.NetworkErrorCodes.Companion.NETWORK_IO_ERROR
import io.jcal.thenewsprovider.repository.datasource.CloudDataSource
import io.jcal.thenewsprovider.repository.datasource.DiskDataSource
import io.jcal.thenewsprovider.repository.mapper.NewsMapper
import io.jcal.thenewsprovider.repository.mapper.model.ArticleModel
import io.jcal.thenewsprovider.repository.mapper.model.ListArticlesModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val diskDataSource: DiskDataSource,
    private val dataMapper: NewsMapper,
    private val deviceUtils: DeviceUtils
) : Repository {
    override fun fetchTopArticles(): LiveData<ListArticlesModel> {
        val responseMediatorLiveData = MediatorLiveData<ListArticlesModel>()
        if (deviceUtils.isNetworkAvailable) {
            responseMediatorLiveData.addSource(cloudDataSource.getTopHeadlines()) { response ->
                if (response != null && response.isSuccessful && response.body != null) {
                    val models = response.body.articles.map { dataMapper.convert(it) }
                    val listTopArtistModel =
                        ListArticlesModel((models as MutableList<ArticleModel>))
                    responseMediatorLiveData.postValue(listTopArtistModel)
                } else {
                    responseMediatorLiveData.postValue(
                        dataMapper.createDomainModel(
                            NETWORK_FAIL_ERROR,
                            ListArticlesModel::class.java
                        )
                    )
                }
            }
        } else {
            responseMediatorLiveData.postValue(
                dataMapper.createDomainModel(
                    NETWORK_IO_ERROR,
                    ListArticlesModel::class.java
                )
            )
        }
        return responseMediatorLiveData
    }

    override fun selectTopArticle(): LiveData<ListArticlesModel> =
        Transformations.map(
            diskDataSource.selectAllTopHeadlines()
        ) { entities ->
            dataMapper.convert(entities)
        }

    override fun insertArticles(listTopArticle: ListArticlesModel): List<Long> =
        diskDataSource.insertArticles(listTopArticle.articles.map { dataMapper.convert(it) })

    override fun insertArticle(article: ArticleModel): Long =
        diskDataSource.insertArticle(dataMapper.convert(article))
}