package io.jcal.thenewsprovider.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.jcal.thenewsprovider.repository.db.dao.TopArticleDao
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity


@Database(entities = [ArticleEntity::class], version = DATABASE_VERSION)
abstract class AppDataBase : RoomDatabase() {

    abstract fun topArticleDao(): TopArticleDao
}