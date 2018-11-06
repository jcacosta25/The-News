package io.jcal.thenewsprovider.repository.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.jcal.thenewsprovider.repository.db.*
import java.util.*

@Entity(tableName = TABLE_NAME_TOP_ARTICLE)
data class ArticleEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ARTICLE_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_ARTICLE_AUTHOR)
    val author: String,

    @ColumnInfo(name = COLUMN_ARTICLE_CONTENT)
    val content: String,

    @ColumnInfo(name = COLUMN_ARTICLE_DESCRIPTION)
    val description: String,

    @ColumnInfo(name = COLUMN_ARTICLE_DATE)
    val date: Date,

    @ColumnInfo(name = COLUMN_ARTICLE_SOURCE_ID)
    val sourceId: String,

    @ColumnInfo(name = COLUMN_ARTICLE_SOURCE_NAME)
    val sourceName: String,

    @ColumnInfo(name = COLUMN_ARTICLE_IS_HEADLINE)
    val isHeadline: Boolean,

    @ColumnInfo(name = COLUMN_ARTICLE_ULR)
    val url: String,

    @ColumnInfo(name = COLUMN_ARTICLE_IMAGE_URL)
    val imageUrl: String
)
