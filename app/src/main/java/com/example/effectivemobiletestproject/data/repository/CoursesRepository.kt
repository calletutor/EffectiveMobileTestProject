package com.example.effectivemobiletestproject.data.repository

import com.example.effectivemobiletestproject.data.remote.CoursesApiService
import com.example.effectivemobiletestproject.data.remote.dto.CourseDto

/**
 * Репозиторий инкапсулирует логику получения курсов из сети.
 * В дальнейшем сюда можно добавить кэширование / работу с БД.
 */
class CoursesRepository(
    private val apiService: CoursesApiService
) {

    /**
     * Вернуть список курсов из API.
     */
    suspend fun getCourses(): List<CourseDto> {
        return apiService.getCourses().courses
    }
}


