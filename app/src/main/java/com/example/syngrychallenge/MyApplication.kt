package com.example.syngrychallenge

import android.app.Application
import com.example.syngrychallenge.di.localDataStoreModule
import com.example.syngrychallenge.di.networkModule
import com.example.syngrychallenge.di.remoteDataSourceModule
import com.example.syngrychallenge.di.repositoryModule
import com.example.syngrychallenge.di.userPreferenceModule
import com.example.syngrychallenge.di.usersUseCaseModule
import com.example.syngrychallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    userPreferenceModule,
                    localDataStoreModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    viewModelModule,
                    usersUseCaseModule
                )
            )
        }
    }
}