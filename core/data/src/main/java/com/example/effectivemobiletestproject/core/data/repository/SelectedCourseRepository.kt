package com.example.effectivemobiletestproject.core.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Репозиторий для работы с выбранными курсами через SQLite напрямую.
 * Находится в core:data, чтобы быть доступным из feature-модулей.
 */
class SelectedCourseRepository(context: Context) {

    private val dbHelper = object : SQLiteOpenHelper(context, "selected_courses.db", null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS selected_courses (courseId INTEGER PRIMARY KEY)")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS selected_courses")
            onCreate(db)
        }
    }

    fun getAllSelectedCourseIds(): Flow<List<Int>> = flow {
        // читаем из БД на IO, а emit делаем в контексте коллектора (Main)
        val ids = withContext(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val cursor = db.query("selected_courses", arrayOf("courseId"), null, null, null, null, null)
            val result = mutableListOf<Int>()
            cursor.use {
                while (it.moveToNext()) {
                    result.add(it.getInt(0))
                }
            }
            result
        }
        emit(ids)
    }

    suspend fun insertSelectedCourse(courseId: Int) {
        withContext(Dispatchers.IO) {
            val db = dbHelper.writableDatabase
            db.insert("selected_courses", null, android.content.ContentValues().apply {
                put("courseId", courseId)
            })
        }
    }

    suspend fun deleteSelectedCourse(courseId: Int) {
        withContext(Dispatchers.IO) {
            val db = dbHelper.writableDatabase
            db.delete("selected_courses", "courseId = ?", arrayOf(courseId.toString()))
        }
    }
}


