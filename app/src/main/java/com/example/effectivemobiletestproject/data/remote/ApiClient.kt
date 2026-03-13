package com.example.effectivemobiletestproject.data.remote

import com.example.effectivemobiletestproject.data.repository.CoursesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Простейший синглтон для настройки Retrofit / OkHttp и доступа к репозиторию курсов.
 *
 * Использование:
 * suspend fun load() {
 *     val courses = ApiClient.coursesRepository.getCourses()
 * }
 */
object ApiClient {

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Базовый URL нужен Retrofit, даже если мы передаём полный @Url в методе сервиса.
     * Здесь можно указать любой корректный URL с тем же доменом.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val coursesApiService: CoursesApiService by lazy {
        retrofit.create(CoursesApiService::class.java)
    }

    val coursesRepository: CoursesRepository by lazy {
        CoursesRepository(coursesApiService)
    }
}


