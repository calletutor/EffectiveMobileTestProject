package com.example.effectivemobiletestproject.di

import com.example.effectivemobiletestproject.core.network.ApiClient
import com.example.effectivemobiletestproject.core.network.api.CoursesApiService
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import org.koin.dsl.module

/**
 * Модуль Koin для сетевых зависимостей
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
}

