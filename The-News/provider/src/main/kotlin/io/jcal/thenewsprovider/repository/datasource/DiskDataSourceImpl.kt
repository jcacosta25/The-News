package io.jcal.thenewsprovider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.thenewsprovider.repository.db.AppDataBase
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity
import javax.inject.Inject

class DiskDataSourceImpl @Inject constructor(private val database: AppDataBase) : DiskDataSource {
    override fun insertArticles(entity: List<ArticleEntity>): List<Long> =
        database.topArticleDao().insertAll(entity)

    override fun insertArticle(entity: ArticleEntity): Long =
        database.topArticleDao().insert(entity)

    override fun selectTopHeadline(articleTitle: String): LiveData<ArticleEntity> =
        database.topArticleDao().selectArticleByTitle(articleTitle)

    override fun selectAllTopHeadlines(): LiveData<List<ArticleEntity>> =
        database.topArticleDao().selectHeadline()
}