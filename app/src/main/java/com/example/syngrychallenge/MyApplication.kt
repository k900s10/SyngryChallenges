package com.example.syngrychallenge

import android.app.Application
import com.example.core.di.localDataStoreModule
import com.example.core.di.networkModule
import com.example.core.di.remoteDataSourceModule
import com.example.core.di.repositoryModule
import com.example.core.di.userDataStoreModule
import com.example.syngrychallenge.di.useCaseModule
import com.example.syngrychallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    userDataStoreModule,
                    localDataStoreModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}