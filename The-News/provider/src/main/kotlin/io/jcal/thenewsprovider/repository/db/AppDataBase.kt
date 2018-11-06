package io.jcal.thenewsprovider.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.jcal.thenewsprovider.repository.db.dao.TopArticleDao
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity
import io.jcal.thenewsprovider.repository.db.entity.DateConverter


@Database(entities = [ArticleEntity::class], version = DATABASE_VERSION)
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun topArticleDao(): TopArticleDao
}