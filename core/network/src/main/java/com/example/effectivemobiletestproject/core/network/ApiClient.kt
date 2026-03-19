package com.example.effectivemobiletestproject.core.network

import com.example.effectivemobiletestproject.core.network.api.CoursesApiService
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Простейший синглтон для настройки Retrofit / OkHttp и доступа к API сервису.
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


