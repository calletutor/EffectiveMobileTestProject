package com.example.effectivemobiletestproject.core.network.api

import com.example.effectivemobiletestproject.core.network.dto.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Retrofit‑сервис для получения списка курсов из мок‑API.
 */
interface CoursesApiService {

    /**
     * Получить все курсы.
     *
     * По умолчанию используется полный URL из ТЗ.
     */
    @GET
    suspend fun getCourses(
        @Url url: String = COURSES_URL
    ): CoursesResponse

    companion object {
        /**
         * Полная ссылка на JSON с курсами.
         *
         * Источник: https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download
         */
        const val COURSES_URL: String =
            "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
    }
}


