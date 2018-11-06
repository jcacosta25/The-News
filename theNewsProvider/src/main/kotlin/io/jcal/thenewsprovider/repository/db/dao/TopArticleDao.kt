package io.jcal.thenewsprovider.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.jcal.thenewsprovider.repository.db.COLUMN_ARTICLE_IS_HEADLINE
import io.jcal.thenewsprovider.repository.db.COLUMN_ARTICLE_TITLE
import io.jcal.thenewsprovider.repository.db.TABLE_NAME_TOP_ARTICLE
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity

@Dao
interface TopArticleDao : BaseDao<ArticleEntity> {

    @Insert(onConflict = IGNORE)
    fun insertAll(entities: List<ArticleEntity>): List<Long>

    @Query("select * from $TABLE_NAME_TOP_ARTICLE where $COLUMN_ARTICLE_TITLE = :articleTitle")
    fun selectArticleByTitle(articleTitle: String): LiveData<ArticleEntity>

    @Query("select * from $TABLE_NAME_TOP_ARTICLE")
    fun selectAll(): LiveData<List<ArticleEntity>>

    @Query("select * from $TABLE_NAME_TOP_ARTICLE where $COLUMN_ARTICLE_IS_HEADLINE = 1")
    fun selectHeadline():LiveData<List<ArticleEntity>>
}