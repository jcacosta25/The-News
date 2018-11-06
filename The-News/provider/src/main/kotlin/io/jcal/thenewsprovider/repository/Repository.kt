package io.jcal.thenewsprovider.repository

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.repository.mapper.model.ArticleModel
import io.jcal.thenewsprovider.repository.mapper.model.ListArticlesModel

interface Repository {

    fun fetchTopArticles(): LiveData<ListArticlesModel>

    fun selectTopArticle(): LiveData<ListArticlesModel>

    fun insertArticles(listTopArticle: ListArticlesModel): List<Long>

    fun insertArticle(article: ArticleModel): Long
}