package com.example.effectivemobiletestproject.di

import com.example.effectivemobiletestproject.core.network.ApiClient
import com.example.effectivemobiletestproject.core.network.api.CoursesApiService
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import com.example.effectivemobiletestproject.core.data.repository.SelectedCourseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Модуль Koin для сетевых зависимостей и базы данных
 */
val appModule = module {
    
    // API Service
    single<CoursesApiService> {
        ApiClient.coursesApiService
    }
    
    // Repository
    single<CoursesRepository> {
        CoursesRepository(get())
    }
    
    // SelectedCourseRepository (SQLite напрямую, из core:data)
    single<SelectedCourseRepository> {
        SelectedCourseRepository(androidContext())
    }
}

