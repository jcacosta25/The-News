package io.jcal.thenewsprovider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity

interface DiskDataSource {
    fun insertArticles(entity: List<ArticleEntity>): List<Long>

    fun insertArticle(entity:ArticleEntity):Long

    fun selectTopHeadline(articleTitle: String): LiveData<ArticleEntity>

    fun selectAllTopHeadlines(): LiveData<List<ArticleEntity>>
}