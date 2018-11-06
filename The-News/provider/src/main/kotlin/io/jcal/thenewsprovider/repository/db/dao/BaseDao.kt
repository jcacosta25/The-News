package io.jcal.thenewsprovider.repository.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE

interface BaseDao<T> {

    @Insert(onConflict = IGNORE)
    fun insert(entity: T): Long

    @Insert(onConflict = REPLACE)
    fun upsert(entity: T): Long

    @Delete
    fun delete(entity: T)
}