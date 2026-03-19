package com.example.effectivemobiletestproject.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity для сохранения выбранных курсов в локальной БД
 */
@Entity(tableName = "selected_courses")
data class SelectedCourseEntity(
    @PrimaryKey
    val courseId: Int
)



