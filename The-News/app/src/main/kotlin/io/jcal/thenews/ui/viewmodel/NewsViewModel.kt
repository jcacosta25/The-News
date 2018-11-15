package io.jcal.thenews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.jcal.thenews.ui.mapper.UiMapper
import io.jcal.thenews.ui.model.ListArticlesUiModel
import io.jcal.thenewsprovider.domain.interactor.UseCaseGetTopArticles
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    val useCaseGetTopArticles: UseCaseGetTopArticles,
    private val mapper: UiMapper
) : ViewModel() {
    private var topNews: MediatorLiveData<ListArticlesUiModel> = MediatorLiveData()

    fun headlineNews(): LiveData<ListArticlesUiModel> {
        if (topNews.value == null) {
            topNews.addSource(Transformations.map(
                useCaseGetTopArticles.asLiveData()
            ) { response ->
                mapper.convert(response.data)
            }) { source -> topNews.postValue(source) }
        }
        return topNews
    }
}