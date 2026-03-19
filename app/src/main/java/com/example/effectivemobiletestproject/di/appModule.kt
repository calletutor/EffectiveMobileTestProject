package com.example.effectivemobiletestproject.di

import com.example.effectivemobiletestproject.core.network.ApiClient
import com.example.effectivemobiletestproject.core.network.api.CoursesApiService
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import com.example.effectivemobiletestproject.core.data.repository.SelectedCourseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    
    single<CoursesApiService> {
        ApiClient.coursesApiService
    }
    
    single<CoursesRepository> {
        CoursesRepository(get())
    }
    
    single<SelectedCourseRepository> {
        SelectedCourseRepository(androidContext())
    }
}
