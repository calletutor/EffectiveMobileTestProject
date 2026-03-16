package com.example.effectivemobiletestproject.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с выбранными курсами
 */
@Dao
interface SelectedCourseDao {
    
    @Query("SELECT courseId FROM selected_courses")
    fun getAllSelectedCourseIds(): Flow<List<Int>>
    
    @Query("SELECT * FROM selected_courses WHERE courseId = :courseId")
    suspend fun getSelectedCourse(courseId: Int): SelectedCourseEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedCourse(course: SelectedCourseEntity)
    
    @Query("DELETE FROM selected_courses WHERE courseId = :courseId")
    suspend fun deleteSelectedCourse(courseId: Int)
    
    @Query("SELECT COUNT(*) > 0 FROM selected_courses WHERE courseId = :courseId")
    suspend fun isCourseSelected(courseId: Int): Boolean
}

