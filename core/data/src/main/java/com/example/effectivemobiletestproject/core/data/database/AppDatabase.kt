package com.example.effectivemobiletestproject.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room Database для локального хранения данных
 */
@Database(
    entities = [SelectedCourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun selectedCourseDao(): SelectedCourseDao
    
    companion object {
        const val DATABASE_NAME = "app_database"
    }
}



