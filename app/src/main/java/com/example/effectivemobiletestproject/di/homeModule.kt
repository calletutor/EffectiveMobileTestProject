package com.example.effectivemobiletestproject.di

import com.example.effectivemobiletestproject.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Модуль Koin для feature:home
 */
val homeModule = module {
    
    // ViewModel
    viewModel { HomeViewModel(get()) }
}

