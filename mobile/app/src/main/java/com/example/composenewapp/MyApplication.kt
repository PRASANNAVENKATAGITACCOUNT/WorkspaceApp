package com.example.composenewapp

import android.app.Application
import com.learning.features.di.moreFeatureDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/*
* Dagger hilt is compile time Dependency Injection Library,
* Koin is not compile time injection, give the dependencies on demand when we run it.
* */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(moreFeatureDataSourceModule)
        }
    }
}