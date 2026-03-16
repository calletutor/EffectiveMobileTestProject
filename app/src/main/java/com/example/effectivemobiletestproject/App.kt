package com.example.effectivemobiletestproject

import android.app.Application
import com.example.effectivemobiletestproject.di.appModule
import com.example.effectivemobiletestproject.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@App)
            modules(appModule, homeModule)
        }
    }
}

