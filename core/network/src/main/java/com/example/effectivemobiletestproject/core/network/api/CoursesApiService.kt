package com.example.effectivemobiletestproject.core.network.api

import com.example.effectivemobiletestproject.core.network.dto.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CoursesApiService {
    @GET
    suspend fun getCourses(
        @Url url: String = COURSES_URL
    ): CoursesResponse

    companion object {
        const val COURSES_URL: String =
            "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
    }
}
